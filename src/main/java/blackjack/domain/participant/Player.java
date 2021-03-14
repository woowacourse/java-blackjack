package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.result.Result;

import java.util.List;

public class Player extends Participant {
    public static final String INVALID_MONEY_ERROR = "금액을 1원이상 입력해주세요.";
    private final int bettingMoney;

    public Player(final String name, final int bettingMoney) {
        super(name);
        validateBettingMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validateBettingMoney(int bettingMoney) {
        if (bettingMoney <= 0) {
            throw new IllegalArgumentException(INVALID_MONEY_ERROR);
        }
    }

    public Result findResult(final Dealer dealer) {
        final int dealerScore = dealer.calculateScore();
        final int playerScore = calculateScore();
        if (dealerScore == playerScore) {
            return Result.DRAW;
        }
        if (isBust() || playerScore < dealerScore && !dealer.isBust()) {
            return Result.LOSE;
        }
        return Result.WIN;
    }

    public int getBettingMoney() {
        return bettingMoney;
    }

    @Override
    public List<Card> getInitialCards() {
        return cards.subList(0, 2);
    }

    @Override
    public boolean checkMoreCardAvailable() {
        return !isBust();
    }
}
