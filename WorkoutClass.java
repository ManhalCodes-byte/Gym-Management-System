// LocalDateTime class import ki gayi hai taake date aur time dono store kar saken
import java.time.LocalDateTime;

// ArrayList import ki gayi hai taake multiple members ke names store kar saken
import java.util.ArrayList;

// WorkoutClass naam ki class banayi gayi hai
public class WorkoutClass {

    // Workout class ka naam store karne ke liye variable
    private String className;

    // Trainer ka naam store karne ke liye variable
    private String trainer;

    // Class ki maximum capacity store karne ke liye variable
    private int capacity;

    // Enrolled members ke names store karne ke liye ArrayList
    private ArrayList<String> enrolledMembers;

    // Class ka schedule (date + time) store karne ke liye variable
    private LocalDateTime schedule;

    // Constructor object create hote waqt initial values set karta hai
    public WorkoutClass(String className, String trainer,
                        int capacity, LocalDateTime schedule) {

        // Constructor parameters ko instance variables mein assign karna
        this.className = className;
        this.trainer = trainer;
        this.capacity = capacity;
        this.schedule = schedule;

        // Empty ArrayList create karna taake members add kiye ja saken
        enrolledMembers = new ArrayList<>();
    }

    // Member ko class mein enroll karne wala method
    public void enrollMember(String memberName) {

        // Check karna ke class mein abhi jagah available hai ya nahi
        if(enrolledMembers.size() < capacity) {

            // Member ka naam list mein add karna
            enrolledMembers.add(memberName);

            // Success message display karna
            System.out.println(memberName + " enrolled successfully.");
        }
        else {

            // Agar capacity full ho to error message display karna
            System.out.println("Class is full.");
        }
    }

    // Class ki tamam information display karne wala method
    public void displayClassInfo() {

        // Heading print karna
        System.out.println("\n--- Workout Class Info ---");

        // Class ka naam display karna
        System.out.println("Class Name: " + className);

        // Trainer ka naam display karna
        System.out.println("Trainer: " + trainer);

        // Maximum capacity display karna
        System.out.println("Capacity: " + capacity);

        // Enrolled members ki list display karna
        System.out.println("Enrolled Members: " + enrolledMembers);

        // Schedule display karna
        System.out.println("Schedule: " + schedule);
    }

    // Program execution yahan se start hoti hai
    public static void main(String[] args) {

        // WorkoutClass ka object create karna
        WorkoutClass yogaClass = new WorkoutClass(

            // Class ka naam
            "Yoga",

            // Trainer ka naam
            "Ali Trainer",

            // Maximum 5 members allowed
            5,

            // Current date aur time ko schedule set karna
            LocalDateTime.now()
        );

        // Ahmed ko class mein enroll karna
        yogaClass.enrollMember("Ahmed");

        // Sara ko class mein enroll karna
        yogaClass.enrollMember("Sara");

        // Class ki tamam information display karna
        yogaClass.displayClassInfo();
    }
}