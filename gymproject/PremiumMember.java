package gymproject;
import javax.swing.JOptionPane;
/**
 * Represents a Premium Member of the gym. Inherits from the abstract class
 * GymMember and adds additional premium features like personal trainer, full
 * payment tracking, and discount management.
 */
public class PremiumMember extends GymMember {

    // Final premium charge fixed at 50000
    private final double premiumCharge;
    private String personalTrainer;// Name of the personal trainer assigned to the member
    private boolean isFullPayment;// Indicates whether full payment has been made
    private double paidAmount;// Amount that has been paid so far
    private double discountAmount;// Discount amount calculated on full payment

    /**
     * Constructor to initialize a PremiumMember object with given details.
     *
     * @param id Member ID
     * @param name Member's name
     * @param location Member's location
     * @param phone Contact number
     * @param email Email address
     * @param gender Gender of the member
     * @param dob Date of birth
     * @param membershipStartDate Date when the membership started
     * @param personalTrainer Assigned personal trainer
     */
    public PremiumMember(int id, String name, String location, String phone, String email, String gender, String dob, String membershipStartDate, String personalTrainer) {
        super(id, name, location, phone, email, gender, dob, membershipStartDate);
        this.premiumCharge = 50000;
        this.personalTrainer = personalTrainer;
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    // Getter method for premiumCharge
    public double getPremiumCharge() {
        return premiumCharge;
    }

    // Getter method for personalTrainer
    public String getPersonalTrainer() {
        return personalTrainer;
    }

    // Getter method for isFullPayment
    public boolean isFullPayment() {
        return isFullPayment;
    }

    // Getter method for paidAmount
    public double getPaidAmount() {
        return paidAmount;
    }

    // Getter method for discountAmount
    public double getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Overrides the abstract method from GymMember to mark attendance.
     * Increases attendance count and loyalty points accordingly.
     */
    @Override
    public void markAttendance() {
        if (activeStatus) {
            
            attendance++;
            loyaltyPoints += 10;
        }
        else{
            JOptionPane.showMessageDialog(null, "Member Id not activated yet!");
        }
    }

    /**
     * Handles payment by the member towards their premium charge.
     *
     * @param paidAmount Amount to be paid
     * @return Message indicating the payment status
     */
    public String payDueAmount(double paidAmount) {
        if (isFullPayment) {
            return "Full Amount is already paid.";
        }

        if (this.paidAmount + paidAmount > premiumCharge) {
            return "More balance paid than the premium charge";
        }

        this.paidAmount += paidAmount;

        double remainingAmount = premiumCharge - this.paidAmount;

        if (remainingAmount == 0) {
            isFullPayment = true;
            return "Successfully Paid the Remaining Balance.";
        }

        return "Payment successful! Remaining Balance: " + remainingAmount;
    }

    /**
     * Calculates discount only if the member has fully paid the premium charge.
     *
     * @return Calculated discount amount
     */
    public double calculateDiscount() {
        if (isFullPayment) {
            discountAmount = premiumCharge * 0.10; //10% discount on premium charge
        } else {
            discountAmount = 0; 
        }
        return discountAmount;
    }

    /**
     * Resets all premium-specific fields and also calls the superclass method
     * to reset general member data.
     */
    public void revertPremiumMember() {
        // call parent class
        super.resetMember();
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    /**
     * Displays all member information, including inherited and premium-specific
     * fields.
     *
     * @return Formatted string containing member details
     */
    @Override
    public String display() {
        return super.display() + "\n"
                + "Personal Trainer: " + personalTrainer + "\n"
                + "Paid Amount: " + paidAmount + "\n"
                + "Full Payment: " + isFullPayment + "\n"
                + "Discount Amount: " + discountAmount + "\n"
                + "Remaining Charge: " + (premiumCharge - getPaidAmount());
    }

}
