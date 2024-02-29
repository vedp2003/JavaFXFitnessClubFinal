package fitnessclubjavafx;

/**
 * Enum class representing the different locations for the fitness club studios.
 *
 * @author Ved Patel, Vivek Manthri
 */
public enum Location {
    BRIDGEWATER("SOMERSET", "08807"),
    EDISON("MIDDLESEX", "08837"),
    FRANKLIN("SOMERSET", "08873"),
    PISCATAWAY("MIDDLESEX", "08854"),
    SOMERVILLE("SOMERSET", "08876");

    private final String county;
    private final String zipCode;

    /**
     * Parameterized constructor requires 2 parameters to create a Location object
     *
     * @param county  the studio location's county
     * @param zipCode the ZIP code for the studio location
     */
    Location(String county, String zipCode) {
        this.county = county;
        this.zipCode = zipCode;
    }

    /**
     * A getter method returns the county of the studio location
     *
     * @return the county of the studio location
     */
    public String getCounty() {
        return county;
    }

    /**
     * A getter method returns the zip code of the studio location
     *
     * @return the zip code of the studio location
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * A getter method returns the name/city of the studio location
     *
     * @return the name/city of the studio location
     */
    public String getName() {
        return this.name();
    }

    /**
     * Returns a string representation of the studio location
     *
     * @return a string with the city name, zipcode, and county of the studio location
     */
    @Override
    public String toString() {
        return this.name() + ", " + this.zipCode + ", " + this.county;
    }
}
