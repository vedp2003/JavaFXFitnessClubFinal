package fitnessclubjavafx;

/**
 * This class represents a generic member of the fitness club.
 * Serves as a super class for different types of memberships (Basic, Family, Premium)
 *
 * @author Ved Patel, Vivek Manthri
 */
public class Member implements Comparable<Member> {

    private Profile profile;
    private Date expire;
    private Location homeStudio;

    /**
     * Parameterized constructor requires 3 parameters to create a Member object
     *
     * @param profile    the profile of the member
     * @param expire     the expiration date of the membership
     * @param homeStudio the home studio location of the member
     */
    public Member(Profile profile, Date expire, Location homeStudio) {
        this.profile = profile;
        this.expire = expire;
        this.homeStudio = homeStudio;
    }

    /**
     * Getter method returns the profile of the member
     *
     * @return the profile of the member
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Getter method returns the home studio location of the member
     *
     * @return the home studio location of the member
     */
    public Location getHomeStudio() {
        return homeStudio;
    }

    /**
     * Calculates the next due membership bill amount.
     * Shows dynamic binding (polymorphism) since subclasses override this
     * to provide specific billing calculations
     *
     * @return the base implementation returns 0.0;
     * when the method is overridden by subclasses, then returns the next due bill amount
     */
    public double bill() {
        return 0.0;
    }

    /**
     * Determines if the membership has expired
     *
     * @return true if the membership is expired; false otherwise
     */
    public boolean isExpired() {
        if (expire.compareTo(new Date()) == -1) {
            return true;
        }
        return false;
    }

    /**
     * Determines if two Member objects are equal
     *
     * @param obj the Member object to be compared
     * @return true if both members have the same profiles and are equal; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member) {
            Member member = (Member) obj;
            return this.getProfile().equals(member.getProfile());
        }
        return false;
    }

    /**
     * Compares two Member objects based on their profile
     *
     * @param o the Member object to be compared
     * @return a negative one, zero, or a positive one depending on if this member is
     * less than, equal to, or greater than the specified member
     */
    @Override
    public int compareTo(Member o) {
        return this.profile.compareTo(o.profile);
    }

    /**
     * Returns a string representation of a Member object
     *
     * @return a string representation of the member
     */
    @Override
    public String toString() {
        String membershipExpiry = "Membership expires ";

        if (isExpired()) {
            membershipExpiry = "Membership expired ";
        }
        return this.profile + ", " + membershipExpiry + this.expire + ", Home Studio: " + homeStudio;
    }
}
