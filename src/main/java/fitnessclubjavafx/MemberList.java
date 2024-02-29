package fitnessclubjavafx;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class defines the ADT MemberList; an instance of Memberlist
 * can hold a list of Member objects
 *
 * @author Ved Patel, Vivek Manthri
 */
public class MemberList {
    private static final int INITIAL_CAPACITY = 4;
    private static final int GROW_CAPACITY = 4;
    private static final int NOT_FOUND = -1;
    private static final int MEMBER_TYPE_INDEX = 0;
    private static final int FIRST_NAME_INDEX = 1;
    private static final int LAST_NAME_INDEX = 2;
    private static final int DOB_INDEX = 3;
    private static final int EXPIRE_INDEX = 4;
    private static final int HOME_STUDIO_INDEX = 5;
    public static final int BASIC_INITIAL_CLASSES = 0;
    public static final int PREMIUM_GUEST_PASS_LIMIT = 3;

    private Member[] members; //holds Basic, Family, or Premium objects
    private int size; //number of objects in the array

    /**
     * Default constructor/no-argument constructor
     */
    public MemberList() {
        members = new Member[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * A getter method returns the list of members
     *
     * @return an array consisting of the list of members
     */
    public Member[] getMembers() {
        return members;
    }

    /**
     * A getter method returns the size of the member list
     *
     * @return the number of members in the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Helper method to find the index of a given member in the list of members
     *
     * @param member the member to find in the list
     * @return the integer index of the member; -1 if not found in the list
     */
    private int find(Member member) {
        for (int index = 0; index < size; index++) {
            if (members[index].compareTo(member) == 0) {
                return index;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Helper method to increase the capacity of the members list by 4
     */
    private void grow() {
        Member[] growMember = new Member[members.length + GROW_CAPACITY];
        for (int i = 0; i < members.length; i++) {
            growMember[i] = members[i];
        }
        members = growMember;
    }

    /**
     * Checks if the list of members contains a given member
     *
     * @param member the member to check
     * @return true if member is in the list; false otherwise
     */
    public boolean contains(Member member) {
        for (int i = 0; i < size; i++) {
            if (members[i] != null && members[i].getProfile().equals(member.getProfile())) {
                return true;
            }
        }
        return false;

    }

    /**
     * Checks if the list of members contains a given profile
     *
     * @param profile the profile to check
     * @return true if profile of the member is in the list; false otherwise
     */
    public boolean containsProfile(Profile profile) {
        for (Member member : this.members) {
            if (member != null && member.getProfile().equals(profile)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the member from the list when given just a profile
     *
     * @param profile the profile of the member to be found
     * @return the Member needed to be retrieved from a given profile
     */
    public Member getMemberFromProfile(Profile profile) {
        int memberIndex = NOT_FOUND;
        for (int i = 0; i < members.length; i++) {
            if (members[i] != null && members[i].getProfile().equals(profile)) {
                memberIndex = i;
                break;
            }
        }
        if(memberIndex == NOT_FOUND) {
            return null;
        }
        return members[memberIndex];
    }

    /**
     * Adds a new member to the list of members
     *
     * @param member the member to add
     * @return true if the member is new and is added; false if member already exists
     */
    public boolean add(Member member) {
        if (contains(member)) {
            return false;
        }
        if (size == members.length) {
            grow();
        }
        members[size] = member;
        size++;
        return true;
    }

    /**
     * Removes a member from the list of members.
     * Maintains the relative order of the events in the array after the deletion
     *
     * @param member the member to remove
     * @return true if the member is new and is removed; false if member already exists
     */
    public boolean remove(Member member) {
        int memberIndex = find(member);
        if (memberIndex == NOT_FOUND) {
            return false;
        }
        for (int i = memberIndex; i < size - 1; i++) {
            members[i] = members[i + 1];
        }
        members[size - 1] = null;
        size--;
        return true;

    }

    /**
     * Loads members from a text file into the list of members.
     *
     * @param file The text file from which to load the members.
     * @throws IOException When an I/O error occurs when trying to access/read the file.
     */
    public void load(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        String inputStr;
        while (scanner.hasNextLine()) {
            inputStr = scanner.nextLine();
            if (inputStr.isEmpty()) {
                inputStr = scanner.nextLine();
            }
            String[] strSplit = inputStr.split("\\s");

            String memberType = strSplit[MEMBER_TYPE_INDEX];
            String firstName = strSplit[FIRST_NAME_INDEX];
            String lastName = strSplit[LAST_NAME_INDEX];
            Date dob = new Date(strSplit[DOB_INDEX]);
            Date expire = new Date(strSplit[EXPIRE_INDEX]);
            Location homeStudio = Location.valueOf(strSplit[HOME_STUDIO_INDEX].toUpperCase());

            Member member;
            Profile memberProfile = new Profile(firstName, lastName, dob);
            if (memberType.equals("B")) {
                member = new Basic(memberProfile, expire, homeStudio, BASIC_INITIAL_CLASSES);
                add(member);
            }
            if (memberType.equals("F")) {
                member = new Family(memberProfile, expire, homeStudio, true);
                add(member);
            }
            if (memberType.equals("P")) {
                member = new Premium(memberProfile, expire, homeStudio, PREMIUM_GUEST_PASS_LIMIT);
                add(member);
            }
        }
        scanner.close();
    }

    /**
     * Sorts all the members in the list sorted by county and then zip code.
     * Uses insertion sort logic to order the members
     */
    public void printByCounty() {
        for (int i = 1; i < size; i++) {
            Member key = members[i];
            int j = i - 1;

            while (j >= 0 && ((members[j].getHomeStudio().getCounty().compareTo(key.getHomeStudio().getCounty()) > 0)
                    || (members[j].getHomeStudio().getCounty().compareTo(key.getHomeStudio().getCounty()) == 0
                    && members[j].getHomeStudio().getZipCode().compareTo(key.getHomeStudio().getZipCode()) > 0))) {

                members[j + 1] = members[j];
                j -= 1;
            }
            members[j + 1] = key;
        }
    }

    /**
     * Sorts all the members in the list sorted by their profiles.
     * Uses insertion sort logic to order the members
     */
    public void printByMember() {
        for (int i = 1; i < size; i++) {
            Member key = members[i];
            int j = i - 1;

            while (j >= 0 && members[j].compareTo(key) == 1) {
                members[j + 1] = members[j];
                j -= 1;
            }
            members[j + 1] = key;
        }
    }


    /**
     * Gets a String array with all the members with their next bill due amounts.
     *
     * @return a String array with members and their bill amounts
     */
    public String[] printFees() {
        String[] feesArray = new String[members.length];
        int i = 0;
        for (Member member : members) {
            if (member != null) {
                feesArray[i++] = member + " [next due: $" + String.format("%.2f", member.bill()) + "]";
            }
        }
        return feesArray;
    }


}
