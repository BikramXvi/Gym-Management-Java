package gymproject; //this is the package named gymproject

public abstract class GymMember {

    protected int id; //protected access modifier so that it is accessible within the same package and by subclasses (even if they are in different package)
    protected String name;
    protected String location;
    protected String phone;
    protected String email;
    protected String gender;
    protected String dob;
    protected String membershipStartDate;
    protected int attendance;
    protected double loyaltyPoints;
    protected boolean activeStatus;

    /**
 * Abstract class representing a Gym Member.
 * Holds common attributes and behaviors for all gym members.
 */
    public GymMember(int id, String name, String location, String phone, String email, String dob, String gender, String membershipStartDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.membershipStartDate = membershipStartDate;
        this.attendance = 0;
        this.loyaltyPoints = 0;
        this.activeStatus = false;
    }

    // Getter method for id
        public int getId() {
            return id;
        }

    // Getter method for name
        public String getName() {
            return name;
        }

    // Getter method for location
        public String getLocation() {
            return location;
        }
    // Getter method for phone

        public String getPhone() {
            return phone;
        }

    // Getter method for email
        public String getEmail() {
            return email;
        }

    // Getter method for gender
        public String getGender() {
            return gender;
        }

    // Getter method for dob
        public String getDob() {
            return dob;
        }

    // Getter method for membershipStartDate
        public String getMembershipStartDate() {
            return membershipStartDate;
        }
    // Getter method for attendance

        public int getAttendance() {
            return attendance;
        }

    // Getter method for loyaltyPoints
        public double getLoyaltyPoints() {
            return loyaltyPoints;
        }

    // Getter method for activeStatus
        public boolean isActiveStatus() {
            return activeStatus;
        }

        /**
         * Abstract method to mark attendance.
         * Must be implemented by subclass
         */
        public abstract void markAttendance();

        /**
     * Activates the membership by setting activeStatus to true.
     */
        public void activateMembership() {
            this.activeStatus = true;
        }

        /**
     * Deactivates the membership if currently active.
     */
        public void deactivateMembership() {
            if (activeStatus) {
                this.activeStatus = false; //sets the activeStatus to false
            } else {
                System.out.println("Already Deactivated or Not activated till now");
            }
        }

        /**
     * Resets the member's status: deactivates, resets attendance and loyalty points.
     */
    public void resetMember() {
        this.activeStatus = false; //resets activeStatus to false
        this.attendance = 0; //resets attendance to 0
        this.loyaltyPoints = 0; //resets loyaltyPoints to 0
    }

    /**
     * Displays the member's information as a string.
     * @return Formatted member details.
     */    public String display() {
        return "Member Id: " + this.id + "\n"
                + "Member Name: " + this.name + "\n"
                + "Location: " + this.location + "\n"
                + "Phone: " + this.phone + "\n"
                + "Email: "+this.email+"\n"
                + "Gender: " + this.gender + "\n"
                + "Date of Birth: " + this.dob + "\n"
                + "Membership Start Date: " + this.membershipStartDate + "\n"
                + "Attendance: " + this.attendance + "\n"
                + "Loyalty Points: " + this.loyaltyPoints + "\n"
                + "Active Status: " + this.activeStatus;
    }

}
