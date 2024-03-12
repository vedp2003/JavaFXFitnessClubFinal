package fitnessclubjavafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This is the user interface class that processes the input and output of data
 *
 * @author Ved Patel, Vivek Manthri
 */
public class StudioManagerController {
    public static final int CANCEL_MEMBERSHIP_INPUT_MAX = 4;
    public static final int ADD_REMOVE_CLASS_INPUT_MAX = 7;
    public static final int ADD_CANCEL_MEMBERSHIP_FIRST_NAME_INDEX = 1;
    public static final int ADD_CANCEL_MEMBERSHIP_LAST_NAME_INDEX = 2;
    public static final int ADD_CANCEL_MEMBERSHIP_DOB_INDEX = 3;
    public static final int ADD_MEMBERSHIP_STUDIO_INDEX = 4;
    public static final int BASIC_EXPIRY_MONTH_LIMIT_INDEX = 1;
    public static final int BASIC_INITIAL_CLASSES = 0;
    public static final int FAMILY_EXPIRY_MONTH_LIMIT_INDEX = 3;
    public static final int PREMIUM_EXPIRY_YEAR_LIMIT_INDEX = 1;
    public static final int PREMIUM_GUEST_PASS_LIMIT = 3;
    public static final int MEMBER_GUEST_CLASS_TYPE_INDEX = 1;
    public static final int MEMBER_GUEST_CLASS_INSTRUCTOR_INDEX = 2;
    public static final int MEMBER_GUEST_CLASS_STUDIO_INDEX = 3;
    public static final int MEMBER_GUEST_FIRST_NAME_INDEX = 4;
    public static final int MEMBER_GUEST_LAST_NAME_INDEX = 5;
    public static final int MEMBER_GUEST_DOB_INDEX = 6;

    private MemberList memberList;
    private Schedule schedule;

    @FXML
    private TextArea outputTextArea;
    @FXML
    private TextField FName_Text;
    @FXML
    private TextField LName_Text;
    @FXML
    private DatePicker DOB;
    @FXML
    private RadioButton basic;
    @FXML
    private RadioButton family;
    @FXML
    private RadioButton premium;
    @FXML
    private ComboBox<String> studioLocationCombo;
    @FXML
    private Button loadMemberButton;
    @FXML
    public TextField FName_Cancel;
    @FXML
    public TextField LName_Cancel;
    @FXML
    public DatePicker DOB_Cancel;
    @FXML
    public Button cancelButton;
    @FXML
    public TextArea outputTextAreaCancel;
    @FXML
    public ComboBox<String> classTypeMemberClass;
    @FXML
    public ComboBox<String> instructorMemberClass;
    @FXML
    public ComboBox<String> locationMemberClass;
    @FXML
    public TextField firstNameMemberClass;
    @FXML
    public TextField lastNameMemberClass;
    @FXML
    public DatePicker dobMemberClass;
    @FXML
    public TextArea outputTextAreaClass;
    @FXML
    private Button LoadClasses_ID;
    @FXML
    public TextArea outputTextAreaPrint;
    @FXML
    private ToggleGroup membershipToggleGroup;
    @FXML
    private TableView<Location> tableLocations;
    @FXML
    private TableColumn<Location, String> col_city;
    @FXML
    private TableColumn<Location, String> col_county;
    @FXML
    private TableColumn<Location, String> col_zip;
    @FXML
    private TableView<String> tableInstructors;
    @FXML
    private TableColumn<String, String> col_instructor_name;
    @FXML
    private TableView<String> tableClassTypes;
    @FXML
    private TableColumn<String, String> col_class_type;


    /**
     * Default constructor/no-argument constructor
     */
    public StudioManagerController() {
        memberList = new MemberList();
        schedule = new Schedule();
    }

    @FXML
    public void initialize() {
        membershipToggleGroup = new ToggleGroup();
        basic.setToggleGroup(membershipToggleGroup);
        family.setToggleGroup(membershipToggleGroup);
        premium.setToggleGroup(membershipToggleGroup);

        FName_Text.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            String typedChar = keyEvent.getCharacter();
            if(!typedChar.matches("[a-zA-Z]")) {
                keyEvent.consume();
            }
        });
        LName_Text.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            String typedChar = keyEvent.getCharacter();
            if(!typedChar.matches("[a-zA-Z]")) {
                keyEvent.consume();
            }
        });
        FName_Cancel.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            String typedChar = keyEvent.getCharacter();
            if(!typedChar.matches("[a-zA-Z]")) {
                keyEvent.consume();
            }
        });
        LName_Cancel.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            String typedChar = keyEvent.getCharacter();
            if(!typedChar.matches("[a-zA-Z]")) {
                keyEvent.consume();
            }
        });
        firstNameMemberClass.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            String typedChar = keyEvent.getCharacter();
            if(!typedChar.matches("[a-zA-Z]")) {
                keyEvent.consume();
            }
        });
        lastNameMemberClass.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            String typedChar = keyEvent.getCharacter();
            if(!typedChar.matches("[a-zA-Z]")) {
                keyEvent.consume();
            }
        });

        col_city.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().name()));
        col_county.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCounty()));
        col_zip.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getZipCode()));
        ObservableList<Location> locations = FXCollections.observableArrayList(Location.values());
        tableLocations.setItems(locations);

        col_instructor_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

        ObservableList<String> instructors = FXCollections.observableArrayList("JENNIFER", "KIM", "DENISE", "DAVIS", "EMMA");
        tableInstructors.setItems(instructors);

        col_class_type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

        ObservableList<String> classTypes = FXCollections.observableArrayList("PILATES", "SPINNING", "CARDIO");
        tableClassTypes.setItems(classTypes);

    }

    @FXML
    protected void onPrintByMemberButtonClick() {
        boolean emptyMemberChecker = true;
        for (Member member : memberList.getMembers()) {
            if (member != null) {
                emptyMemberChecker = false;
                break;
            }
        }
        if (!emptyMemberChecker) {
            outputTextAreaPrint.appendText("\n-list of members sorted by member profiles-\n");
            memberList.printByMember();
            for (Member member : memberList.getMembers()) {
                if (member != null) {
                    outputTextAreaPrint.appendText(member + "\n");
                }
            }
            outputTextAreaPrint.appendText("-end of list-\n\n");
        } else {
            outputTextAreaPrint.appendText("No Members Found - Need to Load/Add Members\n");
        }
    }

    @FXML
    protected void onPrintByCountyButtonClick() {
        boolean emptyMemberChecker = true;
        for (Member member : memberList.getMembers()) {
            if (member != null) {
                emptyMemberChecker = false;
                break;
            }
        }
        if (!emptyMemberChecker) {
            outputTextAreaPrint.appendText("-list of members sorted by county then zipcode-\n");
            memberList.printByCounty();
            for (Member member : memberList.getMembers()) {
                if (member != null) {
                    outputTextAreaPrint.appendText(member + "\n");
                }
            }
            outputTextAreaPrint.appendText("-end of list-\n\n");
        } else {
            outputTextAreaPrint.appendText("No Members Found- Need to Load/Add Members\n");
        }
    }

    @FXML
    protected void onPrintByFeeButtonClick() {

        boolean emptyMemberChecker = true;
        for (Member member : memberList.getMembers()) {
            if (member != null) {
                emptyMemberChecker = false;
                break;
            }
        }
        if (!emptyMemberChecker) {
            outputTextAreaPrint.appendText("\n-list of members with next dues-\n");
            String[] feesArray = memberList.printFees();

            for (int i = 0; i < feesArray.length; i++) {
                outputTextAreaPrint.appendText(feesArray[i] + "\n");
            }

            outputTextAreaPrint.appendText("-end of list-\n\n");
        } else {
            outputTextAreaPrint.appendText("No Members Found - Need to Load/Add Members\n");
        }

    }

    @FXML
    protected void onDisplayScheduleButtonClick() {
        boolean emptyClassChecker = true;
        for (FitnessClass fitnessClass : schedule.getClasses()) {
            if (fitnessClass != null) {
                emptyClassChecker = false;
                break;
            }
        }
        if (!emptyClassChecker) {
            displayClassSchedule();
        } else {
            outputTextAreaPrint.appendText("No Classes Found - Need to Load Classes\n");
        }
    }

    @FXML
    protected void onAddMemberButtonClick() {
        //ASK - if data fields are empty and this button is clicked, can we display Load members first Or do we have to
        //      say that Missing Data Tokens
        if(!loadMemberButton.isDisabled()) {
            outputTextArea.appendText("Load the Members Before Adding a Member!\n");
            return;
        }
        String firstName = FName_Text.getText();
        String lastName = LName_Text.getText();
        LocalDate dateOfBirth = DOB.getValue();
        if (dateOfBirth == null) {
            outputTextArea.appendText("Missing data tokens. Fill all required fields.\n");
            return;
        }
        String formattedDate = dateOfBirth.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        String membershipType = "";
        if (basic.isSelected()) {
            membershipType = "Basic";
        } else if (family.isSelected()) {
            membershipType = "Family";
        } else if (premium.isSelected()) {
            membershipType = "Premium";
        }
        if (membershipType.equals("")) {
            outputTextArea.appendText("Missing data tokens. Fill all required fields.\n");
            return;
        }
        String studioLocation = studioLocationCombo.getValue();
        String[] parts = new String[5];
        parts[1] = firstName;
        parts[2] = lastName;
        parts[3] = formattedDate;
        parts[4] = studioLocation;
        for (int i = 1; i < parts.length; i++) {
            if (parts[i] == null || parts[i].trim().isEmpty()) {
                outputTextArea.appendText("Missing data tokens. Fill all required fields.\n");
                return;
            }
        }

        if (membershipType.equals("Basic")) {
            addBasicMember(parts);
        }
        if (membershipType.equals("Family")) {
            addFamilyMember(parts);
        }
        if (membershipType.equals("Premium")) {
            addPremiumMember(parts);
        }
    }

    @FXML
    protected void onLoadClassesButtonClick() {
        try {

            schedule = new Schedule();
            schedule.load(new File("classSchedule.txt"));
            outputTextAreaClass.appendText("-Fitness classes loaded-\n");
            printClasses();
            outputTextAreaClass.appendText("-end of class list.\n");

            LoadClasses_ID.setDisable(true);

        } catch (IOException e) {
            outputTextAreaClass.setText("ERROR LOADING FILE\n\n");
        }

    }

    @FXML
    protected void onAddMemberToClassButtonClick() {
        if(!LoadClasses_ID.isDisabled()) {
            outputTextAreaClass.appendText("Load the Classes Before Adding Member to Class!\n");
            return;
        }
        String classString = classTypeMemberClass.getValue();
        String instructorString = instructorMemberClass.getValue();
        String studioString = locationMemberClass.getValue();
        String firstName = firstNameMemberClass.getText().trim();
        String lastName = lastNameMemberClass.getText().trim();
        LocalDate dateOfBirth = dobMemberClass.getValue();
        if (dateOfBirth == null) {
            outputTextAreaClass.appendText("Missing data tokens. Fill all required fields.\n");
            return;
        }
        String formattedDate = dateOfBirth.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        String[] parts = new String[7];
        parts[1] = classString;
        parts[2] = instructorString;
        parts[3] = studioString;
        parts[4] = firstName;
        parts[5] = lastName;
        parts[6] = formattedDate;
        for (int i = 1; i < parts.length; i++) {
            if (parts[i] == null || parts[i].trim().isEmpty()) {
                outputTextAreaClass.appendText("Missing data tokens. Fill all required fields.\n");
                return;
            }
        }
        memberClassAttendance(parts);

    }

    @FXML
    protected void onAddGuestToClassButtonClick() {
        if(!LoadClasses_ID.isDisabled()) {
            outputTextAreaClass.appendText("Load the Classes Before Adding Guests to Class!\n");
            return;
        }
        String classString = classTypeMemberClass.getValue();
        String instructorString = instructorMemberClass.getValue();
        String studioString = locationMemberClass.getValue();
        String firstName = firstNameMemberClass.getText().trim();
        String lastName = lastNameMemberClass.getText().trim();
        LocalDate dateOfBirth = dobMemberClass.getValue();
        if (dateOfBirth == null) {
            outputTextAreaClass.appendText("Missing data tokens. Fill all required fields.\n");
            return;
        }
        String formattedDate = dateOfBirth.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        String[] parts = new String[7];
        parts[1] = classString;
        parts[2] = instructorString;
        parts[3] = studioString;
        parts[4] = firstName;
        parts[5] = lastName;
        parts[6] = formattedDate;
        for (int i = 1; i < parts.length; i++) {
            if (parts[i] == null || parts[i].trim().isEmpty()) {
                outputTextAreaClass.appendText("Missing data tokens. Fill all required fields.\n");
                return;
            }
        }
        guestClassAttendance(parts);

    }

    @FXML
    protected void onRemoveMemberFromClassButtonClick() {
        if(!LoadClasses_ID.isDisabled()) {
            outputTextAreaClass.appendText("Load the Classes Before!\n");
            return;
        }
        String classString = classTypeMemberClass.getValue();
        String instructorString = instructorMemberClass.getValue();
        String studioString = locationMemberClass.getValue();
        String firstName = firstNameMemberClass.getText().trim();
        String lastName = lastNameMemberClass.getText().trim();
        LocalDate dateOfBirth = dobMemberClass.getValue();
        if (dateOfBirth == null) {
            outputTextAreaClass.appendText("Missing data tokens. Fill all required fields.\n");
            return;
        }
        String formattedDate = dateOfBirth.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        String[] parts = new String[7];
        parts[1] = classString;
        parts[2] = instructorString;
        parts[3] = studioString;
        parts[4] = firstName;
        parts[5] = lastName;
        parts[6] = formattedDate;

        for (int i = 1; i < parts.length; i++) {
            if (parts[i] == null || parts[i].trim().isEmpty()) {
                outputTextAreaClass.appendText("Missing data tokens. Fill all required fields.\n");
                return;
            }
        }
        removeMemberFromClass(parts);

    }

    @FXML
    protected void onRemoveGuestFromClassButtonClick() {
        if(!LoadClasses_ID.isDisabled()) {
            outputTextAreaClass.appendText("Load the Classes Before!\n");
            return;
        }
        String classString = classTypeMemberClass.getValue();
        String instructorString = instructorMemberClass.getValue();
        String studioString = locationMemberClass.getValue();
        String firstName = firstNameMemberClass.getText().trim();
        String lastName = lastNameMemberClass.getText().trim();
        LocalDate dateOfBirth = dobMemberClass.getValue();
        if (dateOfBirth == null) {
            outputTextAreaClass.appendText("Missing data tokens. Fill all required fields.\n");
            return;
        }
        String formattedDate = dateOfBirth.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        String[] parts = new String[7];
        parts[1] = classString;
        parts[2] = instructorString;
        parts[3] = studioString;
        parts[4] = firstName;
        parts[5] = lastName;
        parts[6] = formattedDate;

        for (int i = 1; i < parts.length; i++) {
            if (parts[i] == null || parts[i].trim().isEmpty()) {
                outputTextAreaClass.appendText("Missing data tokens. Fill all required fields.\n");
                return;
            }
        }
        removeGuestFromClass(parts);

    }

    @FXML
    protected void onCancelButtonClick() {
        String firstName = FName_Cancel.getText().trim();
        String lastName = LName_Cancel.getText().trim();
        LocalDate dateOfBirth = DOB_Cancel.getValue();
        if (dateOfBirth == null) {
            outputTextAreaCancel.appendText("Missing data tokens. Fill all required fields.\n");
            return;
        }
        String formattedDate = dateOfBirth.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String[] parts = new String[4];
        parts[1] = firstName;
        parts[2] = lastName;
        parts[3] = formattedDate;
        for (int i = 1; i < parts.length; i++) {
            if (parts[i] == null || parts[i].trim().isEmpty()) {
                outputTextAreaCancel.appendText("Missing data tokens. Fill all required fields.\n");
                return;
            }
        }
        cancelMembership(parts);
    }

    /**
     * Loads members from memberList.txt and adds members to the list
     */
    @FXML
    protected void onLoadMembersButtonClick() {

        try {
            memberList = new MemberList();
            memberList.load(new File("memberList.txt"));
            outputTextArea.appendText("-list of members loaded-\n");
            printMembers();
            outputTextArea.appendText("-end of list-\n\n");

            loadMemberButton.setDisable(true);

        } catch (IOException e) {
            outputTextArea.setText("ERROR LOADING FILE\n\n");
        }
    }

    /**
     * Helper method to print the members from the list of members
     */
    private void printMembers() {
        for (int i = 0; i < memberList.getSize(); i++) {
            outputTextArea.appendText(memberList.getMembers()[i] + "\n");
        }
    }

    /**
     * Helper method to print the classes from the schedule's list of fitness classes
     */
    private void printClasses() {
        for (int i = 0; i < schedule.getNumClasses(); i++) {
            outputTextAreaClass.appendText(schedule.getClasses()[i] + "\n");
        }
    }

    /**
     * Adds a member with Basic membership to the list of members based on the provided input tokens.
     * Ensures all necessary condition checks before adding a member to the list of members
     *
     * @param parts an array of strings, where each element represents a specific piece of information
     *              from the command line argument
     */
    private void addBasicMember(String[] parts) {
        String firstName = parts[ADD_CANCEL_MEMBERSHIP_FIRST_NAME_INDEX];
        String lastName = parts[ADD_CANCEL_MEMBERSHIP_LAST_NAME_INDEX];
        Date dob;
        try {
            dob = new Date(parts[ADD_CANCEL_MEMBERSHIP_DOB_INDEX]);
        } catch (NumberFormatException e) {
            outputTextArea.appendText("The date contains characters.\n");
            return;
        }
        String studioLocationString = parts[ADD_MEMBERSHIP_STUDIO_INDEX];
        if (!dob.isValid()) {
            outputTextArea.appendText("DOB " + dob + ": invalid calendar date!\n");
            return;
        }
        if (dob.isTodayOrFutureDate()) {
            outputTextArea.appendText("DOB " + dob + ": cannot be today or a future date!\n");
            return;
        }
        if (dob.isLessThan18(dob)) {
            outputTextArea.appendText("DOB " + dob + ": must be 18 or older to join!\n");
            return;
        }
        Location studioLocation;
        try {
            studioLocation = Location.valueOf(studioLocationString.toUpperCase());
        } catch (IllegalArgumentException e) {
            outputTextArea.appendText(studioLocationString + ": invalid studio location!\n");
            return;
        }
        Profile newProfile = new Profile(firstName, lastName, dob);
        if (memberList.containsProfile(newProfile)) {
            outputTextArea.appendText(firstName + " " + lastName + " is already in the member database.\n");
            return;
        }
        Date expireDate = new Date().plusMonths(BASIC_EXPIRY_MONTH_LIMIT_INDEX);
        Member newMember = new Basic(newProfile, expireDate, studioLocation, BASIC_INITIAL_CLASSES);
        memberList.add(newMember);
        outputTextArea.appendText(firstName + " " + lastName + " added.\n");
    }

    /**
     * Adds a member with Family membership to the list of members based on the provided input tokens.
     * Ensures all necessary condition checks before adding a member to the list of members
     *
     * @param parts an array of strings, where each element represents a specific piece of information
     *              from the command line argument
     */
    private void addFamilyMember(String[] parts) {
        String firstName = parts[ADD_CANCEL_MEMBERSHIP_FIRST_NAME_INDEX];
        String lastName = parts[ADD_CANCEL_MEMBERSHIP_LAST_NAME_INDEX];
        Date dob;
        try {
            dob = new Date(parts[ADD_CANCEL_MEMBERSHIP_DOB_INDEX]);
        } catch (NumberFormatException e) {
            outputTextArea.appendText("The date contains characters.\n");
            return;
        }
        String studioLocationString = parts[ADD_MEMBERSHIP_STUDIO_INDEX];
        if (!dob.isValid()) {
            outputTextArea.appendText("DOB " + dob + ": invalid calendar date!\n");
            return;
        }
        if (dob.isTodayOrFutureDate()) {
            outputTextArea.appendText("DOB " + dob + ": cannot be today or a future date!\n");
            return;
        }
        if (dob.isLessThan18(dob)) {
            outputTextArea.appendText("DOB " + dob + ": must be 18 or older to join!\n");
            return;
        }
        Location studioLocation;
        try {
            studioLocation = Location.valueOf(studioLocationString.toUpperCase());
        } catch (IllegalArgumentException e) {
            outputTextArea.appendText(studioLocationString + ": invalid studio location!\n");
            return;
        }
        Profile newProfile = new Profile(firstName, lastName, dob);
        if (memberList.containsProfile(newProfile)) {
            outputTextArea.appendText(firstName + " " + lastName + " is already in the member database.\n");
            return;
        }
        Date expireDate = new Date().plusMonths(FAMILY_EXPIRY_MONTH_LIMIT_INDEX);
        Member newMember = new Family(newProfile, expireDate, studioLocation, true);
        memberList.add(newMember);
        outputTextArea.appendText(firstName + " " + lastName + " added.\n");
    }

    /**
     * Adds a member with Premium membership to the list of members based on the provided input tokens.
     * Ensures all necessary condition checks before adding a member to the list of members
     *
     * @param parts an array of strings, where each element represents a specific piece of information
     *              from the command line argument
     */
    private void addPremiumMember(String[] parts) {
        String firstName = parts[ADD_CANCEL_MEMBERSHIP_FIRST_NAME_INDEX];
        String lastName = parts[ADD_CANCEL_MEMBERSHIP_LAST_NAME_INDEX];
        Date dob;
        try {
            dob = new Date(parts[ADD_CANCEL_MEMBERSHIP_DOB_INDEX]);
        } catch (NumberFormatException e) {
            outputTextArea.appendText("The date contains characters.\n");
            return;
        }
        String studioLocationString = parts[ADD_MEMBERSHIP_STUDIO_INDEX];
        if (!dob.isValid()) {
            outputTextArea.appendText("DOB " + dob + ": invalid calendar date!\n");
            return;
        }
        if (dob.isTodayOrFutureDate()) {
            outputTextArea.appendText("DOB " + dob + ": cannot be today or a future date!\n");
            return;
        }
        if (dob.isLessThan18(dob)) {
            outputTextArea.appendText("DOB " + dob + ": must be 18 or older to join!\n");
            return;
        }
        Location studioLocation;
        try {
            studioLocation = Location.valueOf(studioLocationString.toUpperCase());
        } catch (IllegalArgumentException e) {
            outputTextArea.appendText(studioLocationString + ": invalid studio location!\n");
            return;
        }
        Profile newProfile = new Profile(firstName, lastName, dob);
        if (memberList.containsProfile(newProfile)) {
            outputTextArea.appendText(firstName + " " + lastName + " is already in the member database.\n");
            return;
        }
        Date expireDate = new Date().plusYears(PREMIUM_EXPIRY_YEAR_LIMIT_INDEX);
        Member newMember = new Premium(newProfile, expireDate, studioLocation, PREMIUM_GUEST_PASS_LIMIT);
        memberList.add(newMember);
        outputTextArea.appendText(firstName + " " + lastName + " added.\n");
    }

    /**
     * Cancels the membership and removes the member from the member database.
     * Ensures necessary condition checks before removing a member from the list of members
     *
     * @param parts an array of strings, where each element represents a specific piece of information
     *              from the command line argument
     */
    private void cancelMembership(String[] parts) {
        if (parts.length != CANCEL_MEMBERSHIP_INPUT_MAX) {
            outputTextAreaCancel.appendText("Missing data tokens.\n");
            return;
        }
        String firstName = parts[ADD_CANCEL_MEMBERSHIP_FIRST_NAME_INDEX];
        String lastName = parts[ADD_CANCEL_MEMBERSHIP_LAST_NAME_INDEX];
        Date dob;
        try {
            dob = new Date(parts[ADD_CANCEL_MEMBERSHIP_DOB_INDEX]);
        } catch (NumberFormatException e) {
            outputTextAreaCancel.appendText("The date contains characters.\n");
            return;
        }
        Profile cancelProfile = new Profile(firstName, lastName, dob);

        boolean foundAndRemoved = false;
        for (Member member : memberList.getMembers()) {
            if (member != null && member.getProfile().equals(cancelProfile)) {
                foundAndRemoved = memberList.remove(member);
                break;
            }
        }
        if (foundAndRemoved) {
            outputTextAreaCancel.appendText(firstName + " " + lastName + " removed.\n");
        } else {
            outputTextAreaCancel.appendText(firstName + " " + lastName + " is not in the member database.\n");
        }
    }

    /**
     * Displays the class schedule with current attendees
     */
    private void displayClassSchedule() {
        outputTextAreaPrint.appendText("-Fitness classes-\n");
        for (FitnessClass fitnessClass : schedule.getClasses()) {
            if (fitnessClass != null) {
                outputTextAreaPrint.appendText(fitnessClass + "\n");
                if (fitnessClass.getMembers().getSize() > 0) {
                    outputTextAreaPrint.appendText("[Attendees]\n");
                    for (Member member : fitnessClass.getMembers().getMembers()) {
                        if (member != null) {
                            outputTextAreaPrint.appendText("   " + member + "\n");
                        }
                    }
                }
                if (fitnessClass.getGuests().getSize() > 0) {
                    outputTextAreaPrint.appendText("[Guests]\n");
                    for (Member guest : fitnessClass.getGuests().getMembers()) {
                        if (guest != null) {
                            outputTextAreaPrint.appendText("   " + guest + "\n");
                        }
                    }
                }
            }
        }
        outputTextAreaPrint.appendText("-end of class list.\n");
    }

    /**
     * Takes attendance of a member attending a class and adds the member to the class.
     * Ensures all necessary condition checks before adding a member to a class
     *
     * @param parts an array of strings, where each element represents a specific piece of information
     *              from the command line argument
     */
    private void memberClassAttendance(String[] parts) {

        String classString = parts[MEMBER_GUEST_CLASS_TYPE_INDEX];
        String instructorString = parts[MEMBER_GUEST_CLASS_INSTRUCTOR_INDEX];
        String studioString = parts[MEMBER_GUEST_CLASS_STUDIO_INDEX];
        String firstName = parts[MEMBER_GUEST_FIRST_NAME_INDEX];
        String lastName = parts[MEMBER_GUEST_LAST_NAME_INDEX];
        Date dob = new Date(parts[MEMBER_GUEST_DOB_INDEX]);

        if (!addToClassInputChecker(parts, classString, instructorString, studioString, firstName, lastName, dob)) {
            return;
        }
        Offer classType = Offer.valueOf(classString.toUpperCase());
        Instructor instructor = Instructor.valueOf(instructorString.toUpperCase());
        Location studio = Location.valueOf(studioString.toUpperCase());
        Profile profile = new Profile(firstName, lastName, dob);
        Member member = memberList.getMemberFromProfile(profile);
        if (member instanceof Basic && !member.getHomeStudio().equals(studio)) {
            outputTextAreaClass.appendText(firstName + " " + lastName + " is attending a class at " + studio.getName()
                    + " - [BASIC] home studio at " + member.getHomeStudio().getName() + "\n");
            return;
        }
        FitnessClass targetClass = schedule.findClass(classType, instructor, studio);
        if (targetClass == null) {
            outputTextAreaClass.appendText(classString + " by " + instructorString + " does not exist at " + studioString + "\n");
            return;
        }
        if (targetClass.getMembers().contains(member)) {
            outputTextAreaClass.appendText(member.getProfile().getFname() + " "
                    + member.getProfile().getLname() + " is already in the class.\n");
            return;
        }
        if (timeConflictChecker(targetClass, profile, instructor, studio, member)) {
            return;
        }
        targetClass.addMember(member);
        if (member instanceof Basic) {
            ((Basic) member).addClass();
        }
        outputTextAreaClass.appendText(member.getProfile().getFname() + " " + member.getProfile().getLname()
                + " attendance recorded " + targetClass.getClassInfo() + " at " + targetClass.getStudio() + "\n");
    }

    /**
     * Helper method to check for necessary condition checks for successful addition of a member to a class
     *
     * @param parts            an array of strings, where each element represents a specific piece of information
     *                         from the command line argument
     * @param classString      the class type string
     * @param instructorString the class instructor string
     * @param studioString     the class studio location string
     * @param firstName        first name of the member
     * @param lastName         last name of the member
     * @param dob              date of birth of the member
     * @return true if the input for adding a member to a class is valid; false otherwise
     */
    private boolean addToClassInputChecker(String[] parts, String classString,
                                           String instructorString, String studioString,
                                           String firstName, String lastName, Date dob) {
        if (parts.length != ADD_REMOVE_CLASS_INPUT_MAX) {
            outputTextAreaClass.appendText("Missing data tokens.\n");
            return false;
        }
        Offer classType;
        Instructor instructor;
        Location studio;
        try {
            classType = Offer.valueOf(classString.toUpperCase());
        } catch (IllegalArgumentException e) {
            outputTextAreaClass.appendText(classString + " - class name does not exist.\n");
            return false;
        }
        try {
            instructor = Instructor.valueOf(instructorString.toUpperCase());
        } catch (IllegalArgumentException e) {
            outputTextAreaClass.appendText(instructorString + " - instructor does not exist.\n");
            return false;
        }
        try {
            studio = Location.valueOf(studioString.toUpperCase());
        } catch (IllegalArgumentException e) {
            outputTextAreaClass.appendText(studioString + " - invalid studio location.\n");
            return false;
        }
        Profile profile = new Profile(firstName, lastName, dob);
        if (!memberList.containsProfile(profile)) {
            outputTextAreaClass.appendText(firstName + " " + lastName + " " + dob + " is not in the member database.\n");
            return false;
        }
        Member member = memberList.getMemberFromProfile(profile);
        if (member.isExpired()) {
            outputTextAreaClass.appendText(firstName + " " + lastName + " " + dob + " membership expired.\n");
            return false;
        }
        return true;
    }

    /**
     * Helper method to check for time conflicts based on classes
     * a member may be in at the same time
     *
     * @param targetClass the target FitnessClass in which the member may be in
     * @param profile     the profile of the member
     * @param instructor  the instructor of the class
     * @param studio      the studio location of the class
     * @param member      the member whose time conflicts are checked
     * @return true if the member is currently in a class held at the same time;
     * false if no time conflicts
     */
    private boolean timeConflictChecker(FitnessClass targetClass, Profile profile,
                                        Instructor instructor, Location studio, Member member) {
        for (FitnessClass fitnessClass : schedule.getClasses()) {
            if (fitnessClass != null && !fitnessClass.equals(targetClass)
                    && fitnessClass.getTime().equals(targetClass.getTime())
                    && fitnessClass.getMembers().contains(member)) {
                outputTextAreaClass.appendText("Time conflict - " + profile.getFname() + " " + profile.getLname() +
                        " is in another class held at " + fitnessClass.getTime() + " - " +
                        instructor + ", " + fitnessClass.getTime() + ", " + studio.getName() + "\n");
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a member from a class.
     * Ensures all necessary condition checks before removing a member from a class
     *
     * @param parts an array of strings, where each element represents a specific piece of information
     *              from the command line argument
     */
    private void removeMemberFromClass(String[] parts) {
        if (parts.length != ADD_REMOVE_CLASS_INPUT_MAX) {
            outputTextAreaClass.appendText("Missing data tokens.\n");
            return;
        }

        String classString = parts[MEMBER_GUEST_CLASS_TYPE_INDEX];
        Instructor instructor = Instructor.valueOf(parts[MEMBER_GUEST_CLASS_INSTRUCTOR_INDEX].toUpperCase());
        Location studio = Location.valueOf(parts[MEMBER_GUEST_CLASS_STUDIO_INDEX].toUpperCase());
        String firstName = parts[MEMBER_GUEST_FIRST_NAME_INDEX];
        String lastName = parts[MEMBER_GUEST_LAST_NAME_INDEX];
        Date dob = new Date(parts[MEMBER_GUEST_DOB_INDEX]);
        Profile unregisterProfile = new Profile(firstName, lastName, dob);

        Member unregisterMember = null;
        if (memberList.getMemberFromProfile(unregisterProfile) != null) {
            unregisterMember = memberList.getMemberFromProfile(unregisterProfile);
        } else {
            outputTextAreaClass.appendText(firstName + " " + lastName + " " + dob + " is not in the member database.\n");
            return;
        }
        Offer classType;
        try {
            classType = Offer.valueOf(classString.toUpperCase());
        } catch (IllegalArgumentException e) {
            outputTextAreaClass.appendText(classString + " - class name does not exist.\n");
            return;
        }
        for (FitnessClass fitnessClass : schedule.getClasses()) {
            if (fitnessClass.equals(schedule.findClass(classType, instructor, studio))) {
                boolean removed = fitnessClass.removeMember(unregisterMember);
                if (removed) {
                    outputTextAreaClass.appendText(unregisterProfile.getFname() + " " +
                            unregisterProfile.getLname() + " is removed from " +
                            fitnessClass.getInstructor() + ", " + fitnessClass.getTime() + ", " +
                            fitnessClass.getStudio() + "\n");
                    return;
                } else {
                    outputTextAreaClass.appendText(unregisterProfile.getFname() + " " +
                            unregisterProfile.getLname() + " is not in " +
                            fitnessClass.getInstructor() + ", " + fitnessClass.getTime() +
                            ", " + fitnessClass.getStudio() + "\n");
                }
            }
        }
    }

    /**
     * Takes attendance of a guest attending a class and adds the guest to the class.
     * Ensures all necessary condition checks before adding a guest to a class
     *
     * @param parts an array of strings, where each element represents a specific piece of information
     *              from the command line argument
     */
    private void guestClassAttendance(String[] parts) {

        String classString = parts[MEMBER_GUEST_CLASS_TYPE_INDEX];
        String instructorString = parts[MEMBER_GUEST_CLASS_INSTRUCTOR_INDEX];
        String studioString = parts[MEMBER_GUEST_CLASS_STUDIO_INDEX];
        String firstName = parts[MEMBER_GUEST_FIRST_NAME_INDEX];
        String lastName = parts[MEMBER_GUEST_LAST_NAME_INDEX];
        Date dob = new Date(parts[MEMBER_GUEST_DOB_INDEX]);
        if (!addToClassInputChecker(parts, classString, instructorString,
                studioString, firstName, lastName, dob)) {
            return;
        }
        Offer classType = Offer.valueOf(classString.toUpperCase());
        Instructor instructor = Instructor.valueOf(instructorString.toUpperCase());
        Location studio = Location.valueOf(studioString.toUpperCase());
        Profile profile = new Profile(firstName, lastName, dob);
        Member member = memberList.getMemberFromProfile(profile);
        if (member instanceof Basic) {
            outputTextAreaClass.appendText(firstName + " " + lastName + " [BASIC] - no guest pass.\n");
            return;
        }
        if (!member.getHomeStudio().equals(studio)) {
            outputTextAreaClass.appendText(firstName + " " + lastName + " (guest) is attending a class at " +
                    studio.getName() + " - home studio at " + member.getHomeStudio().getName() + "\n");
            return;
        }
        if (member instanceof Premium && ((Premium) member).getGuestPass() <= 0) {
            outputTextAreaClass.appendText(firstName + " " + lastName + " guest pass not available.\n");
            return;
        }
        if (member instanceof Family && !((Family) member).isGuest()) {
            outputTextAreaClass.appendText(firstName + " " + lastName + " guest pass not available.\n");
            return;
        }
        guestAddToClass(classType, instructor, studio, firstName, lastName, member);
    }

    /**
     * Helper method to add a guest to a fitness class
     *
     * @param classType  the type of the class
     * @param instructor the instructor of the class
     * @param studio     the studio location of the class
     * @param firstName  the first name of the guest
     * @param lastName   the last name of the guest
     * @param member     the guest who is to be added to the class
     */
    private void guestAddToClass(Offer classType, Instructor instructor, Location studio,
                                 String firstName, String lastName, Member member) {
        for (FitnessClass fitnessClass : schedule.getClasses()) {
            if (fitnessClass.equals(schedule.findClass(classType, instructor, studio))) {

                fitnessClass.addGuest(member);
                if (member instanceof Premium) {
                    ((Premium) member).useGuestPass();
                }
                if (member instanceof Family) {
                    ((Family) member).setGuest(false);
                }
                outputTextAreaClass.appendText(firstName + " " + lastName + " (guest) attendance recorded " +
                        classType + " at " + fitnessClass.getStudio() + "\n");

                return;
            }
        }
    }

    /**
     * Removes the guest from a class.
     * Ensures all necessary condition checks before removing a guest from a class
     *
     * @param parts an array of strings, where each element represents a specific piece of information
     *              from the command line argument
     */
    private void removeGuestFromClass(String[] parts) {

        //fixxx coDEEE
        //1) Before loading classes, Press remove Guest From Class with the member not added yet
        //2) After loading classes, Press remove Guest From Class with the member not added yet
        //3) After adding member, press remove member from class does nothing
        //4) Once member is added and then added to class, remove guest from class still removes a guest even tho no guests have been added
        //5) If member is deleted (remove member from class), then clicking add Guest to Class gives me (guest is attending class message)
        //6) If guests are added for a member added to a class, it doesnt display this & it lets u keep pressing Add/Remove Guest
        //3) Add member before Loading Members - it doesnt save the member added before since load member creates new MemberList
        //4) Print Members with Fees - prints 4 null values by default - why??
        //5) When u add a member and then add the member/their guests to class AND then remove the member, the member/guests are still displayed in the class display


        if (parts.length != ADD_REMOVE_CLASS_INPUT_MAX) {
            outputTextAreaClass.appendText("Missing data tokens.\n");
            return;
        }
        String classString = parts[MEMBER_GUEST_CLASS_TYPE_INDEX];
        Instructor instructor = Instructor.valueOf(parts[MEMBER_GUEST_CLASS_INSTRUCTOR_INDEX].toUpperCase());
        Location studio = Location.valueOf(parts[MEMBER_GUEST_CLASS_STUDIO_INDEX].toUpperCase());
        String firstName = parts[MEMBER_GUEST_FIRST_NAME_INDEX];
        String lastName = parts[MEMBER_GUEST_LAST_NAME_INDEX];
        Date dob = new Date(parts[MEMBER_GUEST_DOB_INDEX]);
        Profile profile = new Profile(firstName, lastName, dob);
        Member unregisterGuest = memberList.getMemberFromProfile(profile);


        Offer classType;
        try {
            classType = Offer.valueOf(classString.toUpperCase());
        } catch (IllegalArgumentException e) {
            outputTextAreaClass.appendText(classString + " - class name does not exist.\n");
            return;
        }

        for (FitnessClass fitnessClass : schedule.getClasses()) {
            if (fitnessClass.equals(schedule.findClass(classType, instructor, studio))) {
                boolean removed = fitnessClass.removeGuest(unregisterGuest);
                if(removed) {
                    if (unregisterGuest instanceof Premium) {
                        ((Premium) unregisterGuest).addGuestPass();
                    }
                    if (unregisterGuest instanceof Family) {
                        ((Family) unregisterGuest).setGuest(true);
                    }
                    outputTextAreaClass.appendText(unregisterGuest.getProfile().getFname() + " " +
                            unregisterGuest.getProfile().getLname() + " (guest) is removed from " +
                            fitnessClass.getInstructor() + ", " + fitnessClass.getTime() + ", " +
                            fitnessClass.getStudio() + "\n");
                    return;
                }
                else {
                    outputTextAreaClass.appendText(profile.getFname() + " " +
                            profile.getLname() + " is not in " +
                            fitnessClass.getInstructor() + ", " + fitnessClass.getTime() +
                            ", " + fitnessClass.getStudio() + "\n");
                }
            }
        }
    }
}
