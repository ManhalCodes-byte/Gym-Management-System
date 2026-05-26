import java.time.LocalDate;

public class Member extends Person {

    // Attributes
    private String membershipType;
    private LocalDate joinDate;
    private boolean isActive;
    private String membership;

    // Constructor
    public Member(int id, String name, String email, 
                  String phone, String membershipType, 
                  LocalDate joinDate, boolean isActive, 
                  String membership) {
        super(id, name, email, phone);
        this.membershipType = membershipType;
        this.joinDate = joinDate;
        this.isActive = isActive;
        this.membership = membership;
    }

    // Getters
    public String getMembershipType() { return membershipType; }
    public LocalDate getJoinDate() { return joinDate; }
    public boolean isActive() { return isActive; }
    public String getMembership() { return membership; }

    // Setters
    public void setMembershipType(String membershipType) { 
        this.membershipType = membershipType; }
    public void setJoinDate(LocalDate joinDate) { 
        this.joinDate = joinDate; }
    public void setActive(boolean isActive) { 
        this.isActive = isActive; }
    public void setMembership(String membership) { 
        this.membership = membership; }

    // Override getRole()
    @Override
    public String getRole() {
        return "Member";
    }
}