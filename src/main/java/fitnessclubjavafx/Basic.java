package fitnessclubjavafx;

/**
 * This class represents a basic membership type in the fitness club.
 * This is a subclass of Member and includes specific functionality,
 * such as billing and tracking the number of classes attended.
 *
 * @author Ved Patel, Vivek Manthri
 */
public class Basic extends Member {
    private static final double MONTH_FEE = 39.99;
    private static final double ADDITIONAL_CHARGE = 10.0;
    private static final int CLASSES_LIMIT = 4;

    private int numClasses;

    /**
     * Parameterized constructor requires 4 parameters to create a Basic member object
     *
     * @param profile    the profile of the member
     * @param expire     the expiration date of the membership
     * @param homeStudio the home studio of the member
     * @param numClasses the number of classes attended
     */
    public Basic(Profile profile, Date expire, Location homeStudio, int numClasses) {
        super(profile, expire, homeStudio);
        this.numClasses = numClasses;
    }

    /**
     * A getter method returns the number of classes attended by the member
     *
     * @return the number of classes attended
     */
    public int getNumClasses() {
        return numClasses;
    }

    /**
     * Increments the number of classes attended by the member.
     */
    public void addClass() {
        numClasses++;
    }

    /**
     * Calculates the fee for the Basic membership
     *
     * @return the total bill amount
     */
    @Override
    public double bill() {
        double billAmount = MONTH_FEE;
        if (numClasses > CLASSES_LIMIT) {
            billAmount += ADDITIONAL_CHARGE * (numClasses - CLASSES_LIMIT);
        }
        return billAmount;
    }

    /**
     * Returns a string representation of a Basic member object.
     * Shows dynamic binding (polymorphism)
     *
     * @return a string representation of the Basic member
     */
    @Override
    public String toString() {
        return super.toString()
                + ", (Basic) number of classes attended: " + this.getNumClasses();
    }
}