import java.time.LocalDate;

public class Equipment {

    private String equipmentName;
    private int quantity;
    private String condition;
    private LocalDate lastMaintenanceDate;

    public Equipment(String equipmentName,
                     int quantity,
                     String condition,
                     LocalDate lastMaintenanceDate) {

        this.equipmentName = equipmentName;
        this.quantity = quantity;
        this.condition = condition;
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public void displayEquipmentInfo() {

        System.out.println("\n--- Equipment Info ---");
        System.out.println("Equipment Name: " + equipmentName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Condition: " + condition);
        System.out.println("Last Maintenance: " + lastMaintenanceDate);
    }

    public static void main(String[] args) {

        Equipment treadmill = new Equipment(
            "Treadmill",
            3,
            "Good",
            LocalDate.now()
        );

        treadmill.displayEquipmentInfo();
    }
}