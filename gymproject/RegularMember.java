package gymproject;

import javax.swing.JOptionPane;


/**
 * Represents a Regular Gym Member with plan-based pricing and upgrade eligibility.
 * Inherits common member properties from GymMember.
 */
public class RegularMember extends GymMember {

    private final int ATTENDANCE_LIMIT;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;

    /**
     * Constructor to initialize a RegularMember with provided details.
     *
     * @param id                   Member ID
     * @param name                 Name of the member
     * @param location             Location
     * @param phone                Phone number
     * @param email                Email address
     * @param gender               Gender
     * @param dob                  Date of birth
     * @param membershipStartDate Date of membership start
     * @param referralSource       Source of referral
     */
    public RegularMember(int id, String name, String location, String phone, String email, String gender, String dob, String membershipStartDate, String referralSource) {
        // Calling the parent constructor of GymMember Class using super keyword
        super(id, name, location, phone, email, gender, dob, membershipStartDate);

        // Setting the default values of the attributes of child class
        this.ATTENDANCE_LIMIT=30;
        this.isEligibleForUpgrade = false; // Use the passed parameter
        this.removalReason = ""; // Use the passed parameter
        this.referralSource = referralSource; // Use the passed parameter
        this.plan = "basic"; // Use the passed parameter
        this.price = 6500.0; // Use the passed parameter
    }

    // Getters for member-specific fields
    public int getAttendanceLimit() {
        return ATTENDANCE_LIMIT;
    }

    public boolean getIsEligibleForUpgrade() {
        return isEligibleForUpgrade;
    }

    public String getRemovalReason() {
        return removalReason;
    }

    public String getReferralSource() {
        return referralSource;
    }

    public String getPlan() {
        return plan;
    }

    public double getPrice() {
        return price;
    }

     /**
     * Overrides the abstract method markAttendance.
     * Increments attendance and loyalty points.
     * If attendance reaches the limit, member becomes eligible for upgrade.
     */
    @Override
    public void markAttendance() {
        if (activeStatus) {
            this.attendance++;
            this.loyaltyPoints+=5;
            if(getAttendance()>=getAttendanceLimit()){
                this.isEligibleForUpgrade=true;
            }
        }
    }

    /**
     * Returns the price for a given plan.
     *
     * @param plan Name of the plan (case-insensitive)
     * @return Price of the plan or -1 if invalid
     */
    public double getPlanPrice(String plan) {
        switch (plan.toLowerCase()) //plan is converted to lowercase using String method .toLowerCase() method for flexibility and eradication of unwanted errors due to capital and small letters
        {
            case "basic":
                price = 6500;
                break;

            case "standard":
                price = 12500;
                break;

            case "deluxe":
                price = 18500;

                break;

            default:
                System.out.println("Invalid Plan Name: ");
                System.out.println("Choose among these: Basic, Standard, Deluxe");
                return -1;
        }
        return price;
    }

    /**
     * Upgrades the member's plan if eligible.
     *
     * @param plan Plan to upgrade to
     * @return Result message after attempting upgrade
     */
    public String upgradePlan(String plan) {
        // Check eligibility first
        if (this.isEligibleForUpgrade ) {
            // Handle the plan selection
            if (plan.equals(this.plan)) {
                return "You are already Subscribed to" +this.plan;
            }
            switch (plan) {
                case "Basic":
                    this.price = 6500;
                    this.plan="Basic";
                    break;
                case "Standard":
                    this.price = 12500;
                    this.plan="Standard";
                    break;
                case "Deluxe":
                    this.price = 18000;
                    this.plan="Deluxe";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid plan selected.");
                    return "Invalid plan selected";
            }
            // Successfully upgraded
            return "Plan upgraded successfully to " + plan+ " Price: " +price;
        } else {
            // If not eligible for upgrade
            return "You are not eligible for an upgrade";
        }
    }
    
    
    /**
     * Resets the member data and sets removal reason.
     *
     * @param removalReason Reason for removing or reverting the member
     */
    public void revertRegularMember(String removalReason) {
        super.resetMember();
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        this.removalReason = removalReason;
    }

    /**
     * Displays member details including inherited and RegularMember-specific data.
     *
     * @return Formatted string containing member details
     */
    @Override
    public String display() {
        // Call the base class display and get member info
        String memberInfo = super.display(); // Assuming the super class display() returns a String
        // Append additional info specific to RegularMember
        memberInfo += "\nPlan: " + this.plan + "\n" +
                      "Price: " + this.price;
    
        // Append removal reason if available
        if (this.removalReason != null && !this.removalReason.isBlank()) {
            memberInfo += "\nRemoval Reason: " + this.removalReason;
        }
    
        return memberInfo;
    }
}
