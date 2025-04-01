"""
Train a model using data exported from the Kotlin application and export to ONNX format.
This script is meant to be run after generating training data with the Kotlin application.
"""

import numpy as np
import pandas as pd
import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader, TensorDataset
import onnx
import onnxruntime as ort

# Define the neural network architecture
class EmotionClassifier(nn.Module):
    def __init__(self, input_size, hidden_size=128, output_size=8):
        super(EmotionClassifier, self).__init__()
        self.model = nn.Sequential(
            nn.Linear(input_size, hidden_size),
            nn.ReLU(),
            nn.Dropout(0.2),
            nn.Linear(hidden_size, hidden_size // 2),
            nn.ReLU(),
            nn.Dropout(0.2),
            nn.Linear(hidden_size // 2, output_size),
            nn.Softmax(dim=1)
        )

    def forward(self, x):
        return self.model(x)

def load_data(features_file, labels_file):
    """Load training data from CSV files exported by Kotlin application"""
    # Load features
    features = pd.read_csv(features_file, header=None)

    # Load labels
    labels = pd.read_csv(labels_file, header=None)

    print(f"Loaded {len(features)} training samples")
    return features.values, labels.values.flatten()

def train_model(features, labels, input_size, epochs=50, batch_size=32):
    """Train the PyTorch model"""
    # Convert to PyTorch tensors
    X = torch.tensor(features, dtype=torch.float32)
    y = torch.tensor(labels, dtype=torch.long)

    # Create dataset and data loader
    dataset = TensorDataset(X, y)
    dataloader = DataLoader(dataset, batch_size=batch_size, shuffle=True)

    # Initialize model
    model = EmotionClassifier(input_size=input_size)

    # Loss and optimizer
    criterion = nn.CrossEntropyLoss()
    optimizer = optim.Adam(model.parameters(), lr=0.001)

    # Training loop
    for epoch in range(epochs):
        model.train()
        running_loss = 0.0

        for batch_X, batch_y in dataloader:
            # Zero the parameter gradients
            optimizer.zero_grad()

            # Forward pass
            outputs = model(batch_X)
            loss = criterion(outputs, batch_y)

            # Backward pass and optimize
            loss.backward()
            optimizer.step()

            running_loss += loss.item()

        # Print statistics
        if (epoch + 1) % 5 == 0:
            print(f"Epoch {epoch+1}/{epochs}, Loss: {running_loss/len(dataloader):.4f}")

    print("Training complete!")
    return model

def export_to_onnx(model, input_size, output_file="emotion_classifier_model.onnx"):
    """Export PyTorch model to ONNX format"""
    # Create dummy input tensor
    dummy_input = torch.randn(1, input_size)

    # Export the model
    torch.onnx.export(
        model,                   # PyTorch model
        dummy_input,             # Input tensor
        output_file,             # Output file
        export_params=True,      # Store the trained parameter weights inside the model file
        opset_version=12,        # ONNX version to export to
        do_constant_folding=True,# Optimize constants
        input_names=["input"],   # Input names
        output_names=["output"], # Output names
        dynamic_axes={           # Dynamic axes for variable length sequences
            "input": {0: "batch_size"},
            "output": {0: "batch_size"}
        }
    )

    print(f"Model exported to {output_file}")

    # Verify the model
    onnx_model = onnx.load(output_file)
    onnx.checker.check_model(onnx_model)
    print("ONNX model verified successfully")

    # Test inference with ONNX Runtime
    ort_session = ort.InferenceSession(output_file)
    ort_inputs = {ort_session.get_inputs()[0].name: dummy_input.numpy()}
    ort_outputs = ort_session.run(None, ort_inputs)
    print("ONNX Runtime inference successful")

def main():
    # File paths
    features_file = "/Users/namanmalhotra/IdeaProjects/MoodRelate/training_features.csv"
    labels_file = "/Users/namanmalhotra/IdeaProjects/MoodRelate/training_labels.csv"

    # Load data
    features, labels = load_data(features_file, labels_file)

    # Get input size from features
    input_size = features.shape[1]
    print(f"Input size: {input_size}")

    # Train model
    model = train_model(features, labels, input_size, epochs=50, batch_size=32)

    # Export model to ONNX
    export_to_onnx(model, input_size)

if __name__ == "__main__":
    main()