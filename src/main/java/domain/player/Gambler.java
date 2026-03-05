package domain.player;

import domain.MatchResult;
import expcetion.BlackjackException;
import expcetion.ExceptionMessage;

public class Gambler extends Player {
    private final String name;

    private static final int GAMBLER_NAME_MAX_LENGTH = 10;
    private static final int GAMBLER_NAME_MIN_LENGTH = 2;
    private static final String RESULT_FORMAT = "%s:%s";
    private static final String MATCH_NUMBER_PATTERN = ".*\\d.*";

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
        if (name.matches(MATCH_NUMBER_PATTERN)) {
            throw new BlackjackException(ExceptionMessage.INPUT_ERROR);
        }
    }

    private void validateLength(String name) {
        if (name.length() > GAMBLER_NAME_MAX_LENGTH || name.length() < GAMBLER_NAME_MIN_LENGTH) {
            throw new BlackjackException(ExceptionMessage.INPUT_ERROR);
        }
    }

    public MatchResult getResult(int dealerScore) {
        int score = score();
        if(score > dealerScore)
            return MatchResult.WIN;

        if(score < dealerScore)
            return MatchResult.LOSE;
        return MatchResult.DRAW;
    }

    public String showResult(MatchResult result) {
        return String.format(RESULT_FORMAT,name, result.getName());
    }
}
