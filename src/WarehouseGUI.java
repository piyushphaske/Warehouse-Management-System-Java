import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;

public class WarehouseGUI extends Application {

    // In-memory inventory just like your console app
    private HashMap<String, Integer> inventory = new HashMap<>();

    // GUI components
    private TextField itemNameField;
    private TextField qtyField;
    private TextArea inventoryArea;

    public static void main(String[] args) {
        launch(args); // Start JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("📦 Warehouse Management System");

        // Input fields
        itemNameField = new TextField();
        itemNameField.setPromptText("Item Name");

        qtyField = new TextField();
        qtyField.setPromptText("Quantity");

        // Buttons
        Button addButton = new Button("Add Item");
        Button removeButton = new Button("Remove Item");
        Button viewButton = new Button("View Inventory");
        Button checkPLCButton = new Button("Check Stock Levels");

        // Output area
        inventoryArea = new TextArea();
        inventoryArea.setEditable(false);
        inventoryArea.setPrefHeight(200);

        // Set actions
        addButton.setOnAction(e -> addItem());
        removeButton.setOnAction(e -> removeItem());
        viewButton.setOnAction(e -> viewInventory());
        checkPLCButton.setOnAction(e -> checkPLC());

        // Layout
        HBox inputBox = new HBox(10, itemNameField, qtyField, addButton, removeButton);
        VBox layout = new VBox(10, inputBox, viewButton, checkPLCButton, inventoryArea);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ===== Logic (same as your console app, now in GUI) =====

    private void addItem() {
        String item = itemNameField.getText().trim();
        int qty = getQuantity();

        if (item.isEmpty() || qty < 0) return;

        inventory.put(item, inventory.getOrDefault(item, 0) + qty);
        inventoryArea.setText("✅ Item added: " + item + " (" + qty + " units)");
        clearInputs();
    }

    private void removeItem() {
        String item = itemNameField.getText().trim();
        int requestedQty = getQuantity();

        if (item.isEmpty() || requestedQty <= 0) {
            inventoryArea.setText("⚠️ Please enter a valid item name and quantity.");
            return;
        }

        if (!inventory.containsKey(item)) {
            inventoryArea.setText("❌ Item not found: " + item);
            return;
        }

        int availableQty = inventory.get(item);

        if (requestedQty > availableQty) {
            // Create an alert to warn about insufficient stock
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Insufficient Stock");
            alert.setHeaderText("Not enough items in stock");
            alert.setContentText("You requested " + requestedQty + " units of " + item +
                    " but only " + availableQty + " are available.\n\n" +
                    "Would you like to proceed with removing all " + availableQty + " available units?");

            ButtonType buttonYes = new ButtonType("Yes, remove all available");
            ButtonType buttonNo = new ButtonType("No, cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonYes, buttonNo);

            alert.showAndWait().ifPresent(response -> {
                if (response == buttonYes) {
                    // Remove all available stock
                    inventory.remove(item);
                    inventoryArea.setText("🚚 Shipped all available: " + item + " (" + availableQty + " units)");
                    clearInputs();
                } else {
                    // User canceled, do nothing
                    inventoryArea.setText("⚠️ Operation canceled. " + item + " still has " + availableQty + " units.");
                }
            });
        } else {
            // Normal removal process when sufficient stock exists
            if (requestedQty == availableQty) {
                inventory.remove(item);
                inventoryArea.setText("🚚 Fully shipped: " + item + " (" + requestedQty + " units)");
            } else {
                inventory.put(item, availableQty - requestedQty);
                inventoryArea.setText("🚚 Partial shipment: " + item + " (" + requestedQty + " units)");
            }
            clearInputs();
        }
    }

    private void viewInventory() {
        if (inventory.isEmpty()) {
            inventoryArea.setText("📦 Inventory is empty.");
            return;
        }

        StringBuilder sb = new StringBuilder("📋 Inventory:\n");
        for (String item : inventory.keySet()) {
            sb.append("- ").append(item).append(": ").append(inventory.get(item)).append(" units\n");
        }
        inventoryArea.setText(sb.toString());
    }

    private void checkPLC() {
        StringBuilder sb = new StringBuilder("⚠️ Low Stock Check:\n");
        boolean foundLow = false;

        for (String item : inventory.keySet()) {
            int qty = inventory.get(item);
            if (qty <= 10) {
                sb.append("⚠️ LOW STOCK: ").append(item).append(" (").append(qty).append(" units)\n");
                foundLow = true;
            }
        }

        if (!foundLow) sb.append("✅ All stocks are healthy.");
        inventoryArea.setText(sb.toString());
    }

    private int getQuantity() {
        try {
            return Integer.parseInt(qtyField.getText().trim());
        } catch (NumberFormatException e) {
            inventoryArea.setText("⚠️ Please enter a valid quantity.");
            return -1;
        }
    }

    private void clearInputs() {
        itemNameField.clear();
        qtyField.clear();
    }
}
