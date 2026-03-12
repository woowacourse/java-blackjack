package domain.player;

import domain.BettingMoney;
import domain.MatchResult;
import domain.card.Card;
import exception.BlackjackException;
import exception.ExceptionMessage;
import java.util.List;

public class Gambler extends Player {
    private static final int GAMBLER_NAME_MAX_LENGTH = 10;
    private static final int GAMBLER_NAME_MIN_LENGTH = 2;
    private static final String MATCH_NUMBER_PATTERN = ".*\\d.*";
    private static final String RESULT_FORMAT = "%s:%s";

    private final String name;
    private final BettingMoney bettingMoney;

    public Gambler(String name, BettingMoney bettingMoney) {
        super();
        this.bettingMoney = bettingMoney;
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

    public int calculateFinalIncome(Dealer dealer) {
        MatchResult matchResult = getResult(dealer);
        return matchResult.calculateIncome(this.bettingMoney);
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

    public List<Card> getCardInfo() {
        return handCard.getCards();
    }
}
