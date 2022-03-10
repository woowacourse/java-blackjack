package blackjack;

import blackjack.cards.Cards;
import blackjack.cards.ChangeableCards;

public class Dealer {

    private final ChangeableCards cards;

    public Dealer(Card... cards) {
        this.cards = Cards.softHandCards(cards);
    }

    public Result judge(Cards cards) {
        Score playerScore = cards.score();
        if (playerScore.isBust()) {
            return Result.WIN;
        }
        if (dealerScore().isBust()) {
            return Result.LOSS;
        }
        return compare(playerScore);
    }

    private Score dealerScore() {
        return cards.toMixHand().score();
    }

    private Result compare(Score playerScore) {
        if (dealerScore().lessThan(playerScore)) {
            return Result.LOSS;
        }
        if (dealerScore().moreThan(playerScore)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public boolean isPossibleTakeCard() {
        return cards.score().lessThan(new Score(17));
    }
}
