package fitnessclubjavafx;

/**
 * Represents the details of the profile of a member at the fitness club.
 *
 * @author Ved Patel, Vivek Manthri
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Parameterized constructor requires 3 parameters to create a Profile
     *
     * @param fname the first name
     * @param lname the last name
     * @param dob   the date of birth
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Getter method returns the first name from the profile
     *
     * @return the first name
     */
    public String getFname() {
        return fname;
    }

    /**
     * Getter method returns the last name from the profile
     *
     * @return the last name
     */
    public String getLname() {
        return lname;
    }

    /**
     * Determines if two Profiles objects are equal
     *
     * @param obj the Profile object to be compared
     * @return true if both profiles have the same details and are equal; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj;
            return this.fname.equalsIgnoreCase(profile.fname)
                    && this.lname.equalsIgnoreCase(profile.lname) && this.dob.equals(profile.dob);
        }
        return false;
    }

    /**
     * Compares two Profile objects based on first name, last name, and DOB
     *
     * @param o the profile object to be compared
     * @return a negative one, zero, or a positive one depending on if this profile is
     * less than, equal to, or greater than the specified profile
     */
    @Override
    public int compareTo(Profile o) {
        if (this.lname.compareToIgnoreCase(o.lname) > 0) {
            return 1;
        } else if (this.lname.compareToIgnoreCase(o.lname) < 0) {
            return -1;
        }

        if (this.fname.compareTo(o.fname) > 0) {
            return 1;
        } else if (this.fname.compareTo(o.fname) < 0) {
            return -1;
        }

        if (this.dob.compareTo(o.dob) == 1) {
            return 1;
        } else if (this.dob.compareTo(o.dob) == -1) {
            return -1;
        }
        return 0;
    }

    /**
     * Returns a string representation of a Profile object
     *
     * @return a string for the profile with the format firstName:lastName:DOB
     */
    @Override
    public String toString() {
        return this.fname + ":" + this.lname + ":" + this.dob;
    }
}
