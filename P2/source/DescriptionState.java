/**
 * This class represents a described state in the .fsa file 
 */

public class DescriptionState {
    
    private int startingState;
    private String expression;
    private int transitionState;
    private boolean isAccepting;

    /**
     * Constructor for State class
     * @param startingState state that the description starts from
     * @param expression the String that the description maps from start state to transition state
     * @param transitionState the state that the FSM goes to
     */
    public DescriptionState(int startingState, String expression, int transitionState) {
        this.startingState = startingState;
        this.expression = expression;
        this.transitionState = transitionState;
    }

    /**
     * Method which returns the start state in the description
     * @return the starting state
     */
    public int getStartingState() {
        return startingState;
    }

    /**
     * Method which returns the expression which will change the state
     * @return the expression which changes the state
     */
    public String getExpression() {
        return expression;
    }
    
    /**
     * Method which returns the new state
     * @return the new state
     */
    public int getTransitionState() {
        return transitionState;
    }
}
