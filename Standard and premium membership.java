// StandardMembership
import java.time.LocalDate;

class StandardMembership extends Membership {
    public StandardMembership(String planName, int durationMonths, LocalDate startDate) {
        super(planName, durationMonths, startDate);
    }
    @Override
    public double calculateFee() {
        return 30.0 * getDurationMonths();
    }
}
// PremiumMembership
class PremiumMembership extends Membership {
    private boolean includesPersonalTrainer;
    public PremiumMembership(String planName, int durationMonths,LocalDate startDate,boolean includesPersonalTrainer) {
      super(planName, durationMonths, startDate);
        this.includesPersonalTrainer = includesPersonalTrainer;
    }
    // Getter method
    public boolean isIncludesPersonalTrainer() {
        return includesPersonalTrainer;
    }
    // Setter method
    public void setIncludesPersonalTrainer(boolean includesPersonalTrainer) {
        this.includesPersonalTrainer = includesPersonalTrainer;
    }
    @Override
    public double calculateFee() {
        return 60.0 * getDurationMonths();
    }
}