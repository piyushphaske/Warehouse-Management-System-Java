import java.util.*;

public class WarehouseSystem {

    // 📦 Simulate the Warehouse Inventory
    static HashMap<String, Integer> inventory = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // 🖥️ Simple HMI (menu)
        while (true) {
            System.out.println("\n===== Warehouse HMI =====");
            System.out.println("1. Add Item (Receive)");
            System.out.println("2. Remove Item (Ship)");
            System.out.println("3. View Inventory (SCADA)");
            System.out.println("4. Check Stock Levels (PLC Alert)");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: addItem(); break;
                case 2: removeItem(); break;
                case 3: viewInventory(); break;
                case 4: checkPLC(); break;
                case 5: System.out.println("System shutting down..."); return;
                default: System.out.println("Invalid input.");
            }
        }
    }

    // 🟢 Simulate PAC - Control logic
    static void addItem() {
        System.out.print("Enter item name: ");
        String item = sc.nextLine();
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();
        inventory.put(item, inventory.getOrDefault(item, 0) + qty);
        System.out.println("✅ Item added to inventory.");
    }

    static void removeItem() {
        System.out.print("Enter item name to remove: ");
        String item = sc.nextLine();
        if (!inventory.containsKey(item)) {
            System.out.println("❌ Item not found.");
            return;
        }
        System.out.print("Enter quantity to remove: ");
        int qty = sc.nextInt();
        int currentQty = inventory.get(item);
        if (qty >= currentQty) {
            inventory.remove(item);
            System.out.println("🚚 Item fully shipped.");
        } else {
            inventory.put(item, currentQty - qty);
            System.out.println("🚚 Partial shipment done.");
        }
    }

    // 🖥️ SCADA display
    static void viewInventory() {
        System.out.println("\n📋 Inventory Dashboard:");
        if (inventory.isEmpty()) {
            System.out.println("Warehouse is empty.");
            return;
        }
        for (String item : inventory.keySet()) {
            System.out.println("- " + item + ": " + inventory.get(item) + " units");
        }
    }

    // ⚠️ PLC alerts for low stock
    static void checkPLC() {
        System.out.println("\n⚠️ PLC Stock Level Check:");
        boolean alert = false;
        for (String item : inventory.keySet()) {
            int qty = inventory.get(item);
            if (qty <= 5) {
                System.out.println("⚠️ LOW STOCK: " + item + " (" + qty + " units)");
                alert = true;
            }
        }
        if (!alert) System.out.println("✅ All stocks are healthy.");
    }
}
