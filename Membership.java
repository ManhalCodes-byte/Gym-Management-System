import java.time.LocalDate;
abstract class Membership {
    private String planName;
    private int durationMonths;
    private LocalDate startDate;
    // Constructor
    public Membership(String planName, int durationMonths, LocalDate startDate) {
        this.planName = planName;
        this.durationMonths = durationMonths;
        this.startDate = startDate;
    }

    // Getters and Setters
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getDurationMonths() {
        return durationMonths;
    }
    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    // Abstract Method
    public abstract double calculateFee();
    // Check Membership Validity
    public boolean isValid() {
        LocalDate endDate = startDate.plusMonths(durationMonths);
        return LocalDate.now().isBefore(endDate);
    }
}