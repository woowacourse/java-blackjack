package domain.player;

import expcetion.BlackjackException;
import expcetion.ExceptionMessage;

public class Gambler extends Player {
    private final String name;

    private static final int GAMBLER_NAME_MAX_LENGTH = 10;
    private static final int GAMBLER_NAME_MIN_LENGTH = 2;
    private static final String RESULT_FORMAT = "%s:%s";
    private static final String WIN = "승";
    private static final String LOSE = "패";

    public Gambler(String name) {
        super();
        validate(name);
        this.name = name;
    }


    private void validate(String name) {
        validateContainsNumber(name);
        validateLength(name);
    }

    private void validateContainsNumber(String name) {
        if (name.matches(".*\\d.*")) {
            throw new BlackjackException(ExceptionMessage.INPUT_ERROR);
        }
    }

    private void validateLength(String name) {
        if (name.length() > GAMBLER_NAME_MAX_LENGTH || name.length() < GAMBLER_NAME_MIN_LENGTH) {
            throw new BlackjackException(ExceptionMessage.INPUT_ERROR);
        }
    }

    public boolean isWinner(int dealerScore) {
        return score() > dealerScore;
    }

    public String getResult(boolean isWinner) {
        if(isWinner)
            return String.format(RESULT_FORMAT, name, WIN);

        return String.format(RESULT_FORMAT, name, LOSE);
    }
}
