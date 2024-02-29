package fitnessclubjavafx;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * An instance of this class holds schedule and list of fitness classes
 * loaded from the text file.
 *
 * @author Ved Patel, Vivek Manthri
 */
public class Schedule {
    private static final int TOTAL_CLASSES = 15;
    private static final int CLASS_TYPE_INDEX = 0;
    private static final int INSTRUCTOR_INDEX = 1;
    private static final int TIME_INDEX = 2;
    private static final int LOCATION_INDEX = 3;

    private FitnessClass[] classes;
    private int numClasses;

    /**
     * Default constructor/no-argument constructor.
     * Initializes a new Schedule
     */
    public Schedule() {
        classes = new FitnessClass[TOTAL_CLASSES];
        numClasses = 0;
    }

    /**
     * Getter method returns the all the scheduled fitness classes.
     *
     * @return an array of all the scheduled fitness classes.
     */
    public FitnessClass[] getClasses() {
        return classes;
    }

    /**
     * Getter method returns the number of scheduled fitness classes.
     *
     * @return the total number of scheduled fitness classes.
     */
    public int getNumClasses() {
        return numClasses;
    }

    /**
     * Finds a fitness class from the list based on fitness class details.
     *
     * @param classInfo  The type of class.
     * @param instructor The instructor of the class.
     * @param studio     The studio location of the class.
     * @return The FitnessClass if found; null otherwise.
     */
    public FitnessClass findClass(Offer classInfo, Instructor instructor, Location studio) {
        for (int i = 0; i < numClasses; i++) {
            if (classes[i].getClassInfo().equals(classInfo)
                    && classes[i].getInstructor().equals(instructor) && classes[i].getStudio().equals(studio)) {
                return classes[i];
            }
        }
        return null;
    }

    /**
     * Loads the schedule of fitness classes from a text file into the array of classes.
     *
     * @param file The text file from which to load the classes.
     * @throws IOException When an I/O error occurs when trying to access/read the file.
     */
    public void load(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split("\\s");
            Offer classType = Offer.valueOf(parts[CLASS_TYPE_INDEX].toUpperCase());
            Instructor instructor = Instructor.valueOf(parts[INSTRUCTOR_INDEX].toUpperCase());
            Time time = Time.valueOf(parts[TIME_INDEX].toUpperCase());
            Location location = Location.valueOf(parts[LOCATION_INDEX].toUpperCase());

            classes[numClasses] = new FitnessClass(classType, instructor, location, time);
            numClasses++;
        }
        scanner.close();
    }
}
