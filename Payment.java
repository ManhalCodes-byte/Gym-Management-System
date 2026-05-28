import java.time.LocalDate;

public class Payment {

    // Attributes
    private int paymentId;
    private Member member;
    private double amount;
    private LocalDate paymentDate;
    private String paymentMethod; // Cash / Card / Online

    // Constructor
    public Payment(int paymentId, Member member, 
                   double amount, LocalDate paymentDate, 
                   String paymentMethod) {
        this.paymentId = paymentId;
        this.member = member;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    // Getters
    public int getPaymentId() { return paymentId; }
    public Member getMember() { return member; }
    public double getAmount() { return amount; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public String getPaymentMethod() { return paymentMethod; }

    // Setters
    public void setPaymentId(int paymentId) { 
        this.paymentId = paymentId; }
    public void setMember(Member member) { 
        this.member = member; }
    public void setAmount(double amount) { 
        this.amount = amount; }
    public void setPaymentDate(LocalDate paymentDate) { 
        this.paymentDate = paymentDate; }
    public void setPaymentMethod(String paymentMethod) { 
        this.paymentMethod = paymentMethod; }
}