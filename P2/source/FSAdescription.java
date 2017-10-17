import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class which contains the description of the FSM rules
 */

public class FSAdescription {

    private ArrayList<DescriptionState> descriptionList = new ArrayList<>();
    private ArrayList<Integer> acceptingStates = new ArrayList<>();

    /**
     * Constructor for FSAdescription
     * @param pathToFile the path to the description file
     */
    public FSAdescription(String pathToFile) throws IOException, UnrecognisedCharacterException {
        BufferedReader br = new BufferedReader(new FileReader(pathToFile));
        String line = br.readLine();
    
        while (line != null) {
            DescriptionState state = convertToState(line);
            descriptionList.add(state);
            line = br.readLine();
        }
    }

    /**
     * Method which returns the description list
     * @return the list containing the rules of the FSM
     */
    public ArrayList<DescriptionState> getDescriptionList() {
        return descriptionList;
    }

    /**
     * Method which returns the accepting states
     * @return the list containing the accpeted states
     */
    public ArrayList<Integer> getAcceptingStateList() {
        return acceptingStates;
    }

    /**
     * Method which converts line in the description file to a DescriptionState object
     * @param line the line to convert
     * @return new description state of that line
     */
    private DescriptionState convertToState(String line) throws UnrecognisedCharacterException {
        String[] array = line.split(" ");

        int intialState = convertStringToInt(array[0]);
        String expression = array[1];
        int transitionState = convertStringToInt(array[2]);

        if (array.length > 3) {
            if(array[3].equals("*")) {
                acceptingStates.add(convertStringToInt(array[2]));
            }
        }

        return new DescriptionState(intialState, expression, transitionState);
    }

    /**
     * Method which converts String to integer
     * @param stringToConvert the string that needs converting
     * @return the converted string
     */
    private int convertStringToInt(String stringToConvert) throws UnrecognisedCharacterException {
        int convertedInteger = -1;

        try {
            convertedInteger = Integer.parseInt(stringToConvert);
        } catch (NumberFormatException e) {
            throw new UnrecognisedCharacterException();
        }

        return convertedInteger;
    }
}

