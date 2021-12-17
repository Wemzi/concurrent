package concurent.labs.task;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Enumerating the possible teams.
 *
 */
public class Teams {

    public static final String ARSENAL = "Arsenal";
    public static final String ASTON_VILLA = "Aston Villa";
    public static final String CHELSEA = "Chelsea";
    public static final String EVERTON = "Everton";
    public static final String LIVERPOOL = "Liverpool";
    public static final String MANCHESTER_CITY = "Manchester City";
    public static final String MANCHESTER_UNITED = "Manchester United";
    public static final String NEWCASTLE_UNITED = "Newcastle United";
    public static final String TOTTENHAM_HOTSPUR = "Tottenham Hotspur"; // <insert vomiting emoji here>

    public static final List<String> listOfTeams = Collections.unmodifiableList(Arrays.asList(
            ARSENAL,
            ASTON_VILLA,
            CHELSEA,
            EVERTON,
            LIVERPOOL,
            MANCHESTER_CITY,
            MANCHESTER_UNITED,
            NEWCASTLE_UNITED,
            TOTTENHAM_HOTSPUR
    ));


}
