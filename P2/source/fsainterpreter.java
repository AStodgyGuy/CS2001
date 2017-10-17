import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * This class the main implementation for the fsa interpreter
 */

public class fsainterpreter {

	/**
	 * Main method
	 * @param args - inputs
	 */
	public static void main(String args[]) {
		//args length is not valid
		if (args.length != 1) {
			System.out.println("Usage: java fsainterpreter <fsa description> <input.txt");
		} else {
			if (isAccepting(args[0])) {
				System.out.println("Accepted");
			} else {
				System.out.println("Not accepted");
			}
		}
	}

	/**
	 * Method which determines if the input is accepted by the FSM
	 * @return boolean determined by the FSM
	 */
	public static boolean isAccepting(String pathToDescription) {
		FSM fsm = null;

		try {
			FSAdescription description = new FSAdescription(pathToDescription);
			fsm = new FSM(description);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line = br.readLine();
			char[] charArray = line.toCharArray();

			//loop through the input text
			for (Character ch : charArray) {
				fsm.input(ch.toString());
			}

			//check whether the fsm is in accepting state or not
			if (fsm.getIsAccepting()) {
				return true;
			} else {
				return false;
			}

		//exceptions
		} catch (IOException e) {
			System.out.println("Cannot locate input file!");
			return false;
		} catch (UnrecognisedCharacterException e) {
			return false;
		}		
	}
}

