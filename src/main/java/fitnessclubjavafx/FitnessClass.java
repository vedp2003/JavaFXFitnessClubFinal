package fitnessclubjavafx;

/**
 * An instance of this class holds the information of a fitness class and the attendees for
 * members and guests.
 * It manages the enrollment of members and guests into the class.
 *
 * @author Ved Patel, Vivek Manthri
 */
public class FitnessClass {
    private Offer classInfo;
    private Instructor instructor;
    private Location studio;
    private Time time;
    private MemberList members;
    private MemberList guests;

    /**
     * Parameterized constructor requires 4 parameters to create a FitnessClass object
     *
     * @param classInfo  the type of fitness class.
     * @param instructor the instructor teaching the class.
     * @param studio     the studio location of the class.
     * @param time       the time of the class.
     */
    public FitnessClass(Offer classInfo, Instructor instructor, Location studio, Time time) {
        this.classInfo = classInfo;
        this.instructor = instructor;
        this.studio = studio;
        this.time = time;
        members = new MemberList();
        guests = new MemberList();
    }

    /**
     * A getter method returns the fitness class type
     *
     * @return the type of fitness class
     */
    public Offer getClassInfo() {
        return classInfo;
    }

    /**
     * A getter method returns the instructor teaching the fitness class
     *
     * @return the instructor teaching the class
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * A getter method returns the studio location of the fitness class
     *
     * @return the studio location of the class
     */
    public Location getStudio() {
        return studio;
    }

    /**
     * A getter method returns the time of the fitness class
     *
     * @return the time of the fitness class
     */
    public Time getTime() {
        return time;
    }

    /**
     * A getter method returns the list of members in the fitness class
     *
     * @return the list of members in the class
     */
    public MemberList getMembers() {
        return members;
    }

    /**
     * A getter method returns the list of guests in the fitness class
     *
     * @return the list of guests in the class
     */
    public MemberList getGuests() {
        return guests;
    }

    /**
     * Adds a member to the fitness class.
     *
     * @param member The member to add
     */
    public void addMember(Member member) {
        this.members.add(member);
    }

    /**
     * Removes a member from the fitness class.
     *
     * @param member The member to remove.
     * @return true if removal is successful; false otherwise
     */
    public boolean removeMember(Member member) {
        return this.members.remove(member);
    }

    /**
     * Adds a guest to the fitness class.
     *
     * @param guest The guest to add.
     */
    public void addGuest(Member guest) {
        this.guests.addGuest(guest);
    }

    /**
     * Removes a guest from the fitness class.
     *
     * @param guest The guest to remove.
     * @return true if removal is successful; false otherwise
     */
    public boolean removeGuest(Member guest) {
        return this.guests.remove(guest);
    }

    /**
     * Determines if two FitnessClass objects are equal
     *
     * @param obj the FitnessClass object to be compared
     * @return true if both objects have the same details and are equal; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FitnessClass) {
            FitnessClass fitnessClass = (FitnessClass) obj;
            return this.classInfo == fitnessClass.classInfo &&
                    this.instructor == fitnessClass.instructor &&
                    this.studio == fitnessClass.studio &&
                    this.time == fitnessClass.time;
        }
        return false;
    }

    /**
     * Returns a string representation of the fitness class.
     *
     * @return A string with class type, instructor, time, and studio name.
     */
    @Override
    public String toString() {
        return classInfo + " - " + instructor + ", " + time + ", " + studio.getName();
    }
}
