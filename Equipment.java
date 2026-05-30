// LocalDate class import ki gayi hai taake dates handle kar saken
import java.time.LocalDate;

// Equipment naam ki class banayi gayi hai
public class Equipment {

    // Equipment ka naam store karne ke liye variable
    private String equipmentName;

    // Equipment ki quantity store karne ke liye variable
    private int quantity;

    // Equipment ki condition (Good, Bad, Excellent, etc.) store karne ke liye variable
    private String condition;

    // Last maintenance ki date store karne ke liye variable
    private LocalDate lastMaintenanceDate;

    // Constructor: object create hote waqt values initialize karta hai
    public Equipment(String equipmentName,
                     int quantity,
                     String condition,
                     LocalDate lastMaintenanceDate) {

        // Constructor parameter ko instance variable mein assign karna
        this.equipmentName = equipmentName;

        // Quantity assign karna
        this.quantity = quantity;

        // Condition assign karna
        this.condition = condition;

        // Maintenance date assign karna
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    // Equipment ki information display karne wala method
    public void displayEquipmentInfo() {

        // Heading print karna
        System.out.println("\n--- Equipment Info ---");

        // Equipment ka naam display karna
        System.out.println("Equipment Name: " + equipmentName);

        // Quantity display karna
        System.out.println("Quantity: " + quantity);

        // Condition display karna
        System.out.println("Condition: " + condition);

        // Last maintenance date display karna
        System.out.println("Last Maintenance: " + lastMaintenanceDate);
    }

    // Program execution yahan se start hoti hai
    public static void main(String[] args) {

        // Equipment class ka object create kiya ja raha hai
        Equipment treadmill = new Equipment(

            // Equipment ka naam
            "Treadmill",

            // Available quantity
            3,

            // Current condition
            "Good",

            // Aaj ki date maintenance date ke taur par set karna
            LocalDate.now()
        );

        // Object ke through equipment ki information display karna
        treadmill.displayEquipmentInfo();
    }
}