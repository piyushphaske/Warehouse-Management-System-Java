# Warehouse Management System

A JavaFX application for managing inventory in a warehouse system.

## Features
- Add items to inventory with quantities
- Remove items from inventory
- View current inventory levels
- Check for items with low stock levels (5 or fewer units)
- Warning and confirmation when attempting to remove more items than available

## Setup Instructions
1. Clone this repository
2. Ensure you have JDK 17+ installed
3. Set up JavaFX by either:
   - Adding the JavaFX libraries to your build path
   - Using Maven/Gradle dependencies
   - Adding VM arguments: `--module-pat# Warehouse Management System with Industrial Automation Integration

This project implements a modern warehouse management system with a JavaFX-based HMI (Human-Machine Interface) that simulates integration with industrial automation systems commonly used in manufacturing and logistics environments.

## Overview

The Warehouse Management System provides a user-friendly interface for inventory management while demonstrating concepts of industrial automation integration. It showcases how a software system might interact with PLC (Programmable Logic Controller), PAC (Programmable Automation Controller), and SCADA (Supervisory Control and Data Acquisition) systems in a real-world warehouse environment.

## Features

- **Intuitive HMI Interface**: User-friendly JavaFX GUI for warehouse operators
- **Real-time Inventory Management**:
  - Add items to inventory with specified quantities
  - Remove items with quantity validation and intelligent handling of stock limitations
  - View complete inventory status
- **Low Stock Alerts**: Automated monitoring system that flags items when stock levels fall below threshold (≤ 10 units)
- **PLC Integration Simulation**: Models how the system would interact with PLCs that control physical warehouse equipment
- **SCADA-Compatible Architecture**: Designed with consideration for integration into larger SCADA systems

## Technical Implementation

### HMI (Human-Machine Interface)
The system implements a complete HMI using JavaFX that:
- Provides intuitive controls for warehouse operations
- Displays real-time inventory status
- Shows alerts through a dedicated notification area
- Features confirmation dialogs for critical operations
- Updates interface elements in response to system state changes

### PLC Integration Concepts
While this demonstration uses in-memory data structures, the system architecture shows how it would connect to PLCs in a production environment:
- The "Check Stock Levels" function simulates PLC communication that would monitor physical inventory levels
- Alert thresholds (≤ 10 units) represent how the system would trigger automated restocking processes in a connected material handling system
- The removal confirmation dialog demonstrates safety interlocks that would prevent operations that could damage equipment or create unsafe conditions

### PAC Integration Concepts
The system demonstrates PAC integration concepts through:
- Centralized inventory management that could connect to distributed PACs controlling different zones of a warehouse
- Data validation logic that would prevent invalid commands from being sent to automated equipment
- Error handling techniques that would ensure safe operation in an automated environment

### SCADA Compatibility
The architecture supports future integration with SCADA systems by:
- Providing clear status information that could be visualized on facility-wide dashboards
- Implementing event-driven programming that aligns with SCADA communication patterns
- Supporting alert conditions that could propagate to higher-level monitoring systems

## Setup Instructions

1. Clone this repository
2. Ensure you have JDK 17 or higher installed
3. Set up JavaFX using one of these methods:
   - Configure VM arguments: `--module-path "/path/to/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml`
   - Add JavaFX libraries to your build path
   - Use Maven/Gradle dependencies

### Running with JavaFX

```bash
# Example for Windows
java --module-path "D:\path\to\javafx-sdk\lib" --add-modules javafx.controls,javafx.fxml -jar warehouse-management-system.jar
```

## System Requirements

- Java 17 or higher
- JavaFX 21.0.7 or compatible version
- 4GB RAM minimum (8GB recommended for larger inventories)
- Windows/macOS/Linux operating system

## Future Enhancements

- OPC UA protocol integration for direct communication with industrial controllers
- Database persistence for inventory data
- Multi-user support with role-based access control
- REST API for integration with ERP systems
- Mobile companion application for warehouse staff
- Barcode/RFID scanner integration
- Automated reporting system
- Historical data analysis and forecasting

## License

[MIT License](LICENSE)

## Acknowledgments

- JavaFX framework for enabling the creation of rich industrial HMIs
- Modern industrial automation principles that inspired the architectureh "/path/to/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml`

## Screenshots
[Add screenshots here]

## Future Improvements
- Database integration
- User authentication
- Export/import functionality
- Advanced reporting
