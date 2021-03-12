package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.result.Result;

import java.util.List;

public class Player extends Participant {
    public Player(final String name) {
        super(name);
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

    @Override
    public List<Card> showInitialCards() {
        return cards.subList(0, 2);
    }

    @Override
    public boolean checkMoreCardAvailable() {
        return !isBust();
    }
}
