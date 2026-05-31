import java.util.List;

// Trainer class inherits from Person class
public class Trainer extends Person {

    // Trainer's area of expertise
    private String specialization;

    // List of members assigned to the trainer
    private List<Member> assignedMembers;

    // Trainer's salary per hour
    private double salaryPerHour;

    // Returns the trainer's specialization
    public String getSpecialization() {
        return specialization;
    }

    // Sets the trainer's specialization
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // Returns the list of assigned members
    public List<Member> getAssignedMembers() {
        return assignedMembers;
    }

    // Sets the list of assigned members
    public void setAssignedMembers(List<Member> assignedMembers) {
        this.assignedMembers = assignedMembers;
    }

    // Returns the trainer's salary per hour
    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    // Sets the trainer's salary per hour
    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    // Returns the role of this object
    @Override
    public String getRole() {
        return "Trainer";
    }
}