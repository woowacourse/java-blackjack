package domain.player;

import domain.MatchResult;
import expcetion.BlackjackException;
import expcetion.ExceptionMessage;

public class Gambler extends Player {

    private static final int GAMBLER_NAME_MAX_LENGTH = 10;
    private static final int GAMBLER_NAME_MIN_LENGTH = 2;
    private static final String MATCH_NUMBER_PATTERN = ".*\\d.*";

    public Gambler(String name) {
        super(validate(name));
    }

    private static String validate(String name) {
        validateContainsNumber(name);
        validateLength(name);
        return name;
    }

    private static void validateContainsNumber(String name) {
        if (name.matches(MATCH_NUMBER_PATTERN)) {
            throw new BlackjackException(ExceptionMessage.NAME_NOT_INTEGER_ERROR);
        }
    }

    private static void validateLength(String name) {
        if (name.length() > GAMBLER_NAME_MAX_LENGTH || name.length() < GAMBLER_NAME_MIN_LENGTH) {
            throw new BlackjackException(ExceptionMessage.NAME_LENGTH_ERROR);
        }
    }

    public MatchResult getResult(int dealerScore) {
        return MatchResult.of(score(), dealerScore);
    }

}
