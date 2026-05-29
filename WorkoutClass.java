import java.time.LocalDateTime;
import java.util.ArrayList;

public class WorkoutClass {

    private String className;
    private String trainer;
    private int capacity;
    private ArrayList<String> enrolledMembers;
    private LocalDateTime schedule;

    public WorkoutClass(String className, String trainer,
                        int capacity, LocalDateTime schedule) {

        this.className = className;
        this.trainer = trainer;
        this.capacity = capacity;
        this.schedule = schedule;

        enrolledMembers = new ArrayList<>();
    }

    public void enrollMember(String memberName) {

        if(enrolledMembers.size() < capacity) {
            enrolledMembers.add(memberName);
            System.out.println(memberName + " enrolled successfully.");
        }
        else {
            System.out.println("Class is full.");
        }
    }

    public void displayClassInfo() {

        System.out.println("\n--- Workout Class Info ---");
        System.out.println("Class Name: " + className);
        System.out.println("Trainer: " + trainer);
        System.out.println("Capacity: " + capacity);
        System.out.println("Enrolled Members: " + enrolledMembers);
        System.out.println("Schedule: " + schedule);
    }

    public static void main(String[] args) {

        WorkoutClass yogaClass = new WorkoutClass(
            "Yoga",
            "Ali Trainer",
            5,
            LocalDateTime.now()
        );

        yogaClass.enrollMember("Ahmed");
        yogaClass.enrollMember("Sara");

        yogaClass.displayClassInfo();
    }
}