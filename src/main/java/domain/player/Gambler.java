package domain.player;

import domain.MatchResult;
import dto.GamblerCardInfo;
import expcetion.BlackjackException;
import expcetion.ExceptionMessage;

public class Gambler extends Player {
    private static final int GAMBLER_NAME_MAX_LENGTH = 10;
    private static final int GAMBLER_NAME_MIN_LENGTH = 2;
    private static final String RESULT_FORMAT = "%s:%s";
    private static final String MATCH_NUMBER_PATTERN = ".*\\d.*";
    private final String name;

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

    public MatchResult getResult(Dealer dealer) {
        int gamblerScore = normalize(score());
        int dealerScore = normalize(dealer.score());
        if (gamblerScore > dealerScore) {
            return MatchResult.WIN;
        }

        if (gamblerScore < dealerScore) {
            return MatchResult.LOSE;
        }
        return MatchResult.DRAW;
    }

    private int normalize(int score) {
        if (score > BLACKJACK_MAX_LIMIT) {
            return 0;
        }
        return score;
    }


    public String showResult(MatchResult result) {
        return String.format(RESULT_FORMAT, name, result.getName());
    }

    public String getName() {
        return name;
    }

    public GamblerCardInfo getCardInfo() {
        return new GamblerCardInfo(name, handCard.getCardInfos(), score());
    }

}
