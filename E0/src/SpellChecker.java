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
     * Main method
     * 
     * @param args the word user wants to check
     */
    public void runChecker(String[] args) {
        try {
            String word = args[0].toLowerCase();
            Arrays.sort(dictionary);
            SpellCheckResult result = check(word);
            if (result.isCorrect()) {
                System.out.println(word + " correct");
            } else {
                System.out.println(word + " not found - nearest neighbour(s) " + result.getBefore() + " and " + result.getAfter());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Usage: java SpellChecker <word_to_check>");
        }
        
    }

    /**
     * Main method
     * 
     * @param args the word user wants to check
     */
    public SpellCheckResult check(String word) {
        int index = Arrays.binarySearch(dictionary, word);
        SpellCheckResult scr;
        //add error checker for index out of bounds
        if (index > 0) {
            scr = new SpellCheckResult(true, dictionary[index - 1], dictionary[index + 1]);
        } else {
            scr = new SpellCheckResult(false, dictionary[-index - 1], dictionary[-index + 1]);
        }

        return scr;
    }
 }