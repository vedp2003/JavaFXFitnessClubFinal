package fitnessclubjavafx;

/**
 * This class represents a family membership type in the fitness club.
 * This is a subclass of Member and includes specific functionality,
 * such as billing and guest pass.
 *
 * @author Ved Patel, Vivek Manthri
 */
public class Family extends Member {
    private static final double MONTH_FEE = 49.99;
    private static final int BILL_MONTHS = 3;

    private boolean guest;

    /**
     * Parameterized constructor requires 4 parameters to create a Family member object
     *
     * @param profile    the profile of the family member
     * @param expire     the expiration date of the membership
     * @param homeStudio the home studio of the family member
     * @param guest      boolean indicating if a guest pass is available
     */
    public Family(Profile profile, Date expire, Location homeStudio, boolean guest) {
        super(profile, expire, homeStudio);
        this.guest = guest;
    }

    /**
     * Getter method returns whether a guest pass is available for the family membership.
     *
     * @return true if a guest pass is available; false otherwise
     */
    public boolean isGuest() {
        return guest;
    }

    /**
     * Setter method to set the availability of a guest pass for this family membership.
     *
     * @param guest true to indicate a guest pass is available, false otherwise
     */
    public void setGuest(boolean guest) {
        this.guest = guest;
    }

    /**
     * Calculates the fee for the Family membership
     *
     * @return the total bill amount
     */
    @Override
    public double bill() {
        return MONTH_FEE * BILL_MONTHS;
    }

    /**
     * Returns a string representation of a Family member object.
     * Shows dynamic binding (polymorphism)
     *
     * @return a string representation of the Family member
     */
    @Override
    public String toString() {

        String guestNumber = "0";

        if (isGuest()) {
            guestNumber = "1";
        }

        if (isExpired()) {
            guestNumber = "not eligible";
        }
        return super.toString() + ", (Family) guest-pass remaining: " + guestNumber;
    }
}
