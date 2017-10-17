/**
 * This class represents a simple Finite State Machine (FSM)
 */

public class FSM {

    private int currentState = 1;
    private FSAdescription description;
    private boolean isAccepting = false;

    public FSM(FSAdescription description) {
        this.description = description;
    }

    /**
     * Method which changes the state based on the input seen
     * @param s the input string seen
     */
    public void input(String s) throws UnrecognisedCharacterException{

        //for every state in the fsa description class
        for (DescriptionState ds : description.getDescriptionList()){
            //find states which contain both the current state and the expression s
            if (ds.getStartingState() == currentState && ds.getExpression().equals(s)) {
                //change currentstate to the new state
                currentState = ds.getTransitionState();
                return;
            }
        }
        
        //if character is not recognised, throw new UnrecognisedCharacterException
        throw new UnrecognisedCharacterException();
    }

    /**
     * Method which returns a boolean whether the FSM is in accepting state or not
     * @return boolean whether the FSM is in accepting state or not
     */
    public boolean getIsAccepting() {
        if (description.getAcceptingStateList().contains(currentState)) {
            isAccepting = true;
        }

        return isAccepting;
    }
}

