package fitnessclubjavafx;

/**
 * This class represents a premium membership type in the fitness club.
 * This is a subclass of Member and includes specific functionality,
 * such as billing and tracking the number of guest passes.
 *
 * @author Ved Patel, Vivek Manthri
 */
public class Premium extends Member {
    private static final double MONTH_FEE = 59.99;
    private static final int TOTAL_MONTHS = 12;
    private static final int FREE_MONTH = 1;

    private int guestPass;

    /**
     * Parameterized constructor requires 4 parameters to create a Premium member object
     *
     * @param profile    the profile of the family member
     * @param expire     the expiration date of the membership
     * @param homeStudio the home studio of the family member
     * @param guestPass  the number of guests passes
     */
    public Premium(Profile profile, Date expire, Location homeStudio, int guestPass) {
        super(profile, expire, homeStudio);
        this.guestPass = guestPass;
    }

    /**
     * A getter method returns the number of guests passes available
     *
     * @return the number of guests passes available
     */
    public int getGuestPass() {
        return guestPass;
    }

    /**
     * Decrements the number of guest passes available
     */
    public void useGuestPass() {
        guestPass--;
    }

    /**
     * Increments the number of guest passes available
     */
    public void addGuestPass() {
        guestPass++;
    }

    /**
     * Calculates the fee for the Premium membership
     *
     * @return the total bill amount
     */
    @Override
    public double bill() {
        return MONTH_FEE * (TOTAL_MONTHS - FREE_MONTH);
    }

    /**
     * Returns a string representation of a Premium member object.
     * Shows dynamic binding (polymorphism)
     *
     * @return a string representation of the Premium member
     */
    @Override
    public String toString() {
        String result;

        if (isExpired()) {
            result = "not eligible";
        } else {
            result = "" + this.getGuestPass();
        }
        return super.toString() + ", (Premium) guest-pass remaining: " + result;

    }
}
