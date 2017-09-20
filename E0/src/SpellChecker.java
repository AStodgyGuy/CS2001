import java.util.Collections;
import java.util.Arrays;

/**
 * This class contains code the run the program
 * 
 * @author someone
 *
 */

 public class SpellChecker implements ISpellChecker {

    String[] dictionary;

    /**
     * This is the constructor for the SpellChecker object
     */
    public SpellChecker() {
        this.dictionary = DictionaryLoader.getInstance().loadDictionary();
    }

    /**
     * Main method
     * 
     * @param args the word user wants to check
     */
    public static void main(String[] args) {
        SpellChecker sc = new SpellChecker();
        sc.runChecker(args);
    }

    /**
     * Method which runs the spell checker
     * 
     * @param args the inputted string the user wants to look up
     */
    public void runChecker(String[] args) {
        try {
            String word = args[0].toLowerCase();
            Arrays.sort(dictionary);
            SpellCheckResult result = check(word);

            if (result.isCorrect()) {
                System.out.println(word + " correct");
            } else {
                //messages if the word does not exist
                if (result.getBefore().equals("")) {
                    System.out.println(word + " not found - nearest neighbour " + result.getAfter());
                } else if (result.getAfter().equals("")) {
                    System.out.println(word + " not found - nearest neighbour " + result.getBefore());
                } else { 
                    System.out.println(word + " not found - nearest neighbour(s) " + result.getBefore() + " and " + result.getAfter());
                }
            }
        //word doesn't exist
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Usage: java SpellChecker <word_to_check>");
        }
        
    }

    /**
     * Method which searches for the word in the dictionary
     * 
     * @param word the word user wants to check
     */
    public SpellCheckResult check(String word) {
        int index = Arrays.binarySearch(dictionary, word);
        SpellCheckResult scr = null;

        if (index > 0) {
            scr = new SpellCheckResult(true, dictionary[index - 1], dictionary[index + 1]);
        } else {
            //error catching for binary search
            if (-index + 1 > dictionary.length) {
                scr = new SpellCheckResult(false, dictionary[index - 1], "");
            } else if (-index - 1 < 0) { 
                scr = new SpellCheckResult(false, "", dictionary[index + 1]);
            }
        }

        return scr;
    }
 }