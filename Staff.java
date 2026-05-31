// class
public class Staff extends Person {

    // Position of staff
    private String position;

    // Salary of staff
    private double salary;

    // Get position
    public String getPosition() {
        return position;
    }

    // Set position
    public void setPosition(String position) {
        this.position = position;
    }

    // Get salary
    public double getSalary() {
        return salary;
    }

    // Set salary
    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Return role
    @Override
    public String getRole() {
        return "Staff";
    }
}