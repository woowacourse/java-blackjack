package blackjack;

import blackjack.cards.Cards;
import blackjack.cards.ChangeableCards;

public class Dealer {

    private final ChangeableCards cards;

    public Dealer(Card... cards) {
        this.cards = Cards.softHandCards(cards);
    }

    public void take(Card card) {
        cards.take(card);
    }

    public Result match(Cards cards) {
        Score playerScore = cards.score();
        if (playerScore.isBust()) {
            return Result.WIN;
        }
        if (score().isBust()) {
            return Result.LOSS;
        }
        return compare(playerScore);
    }

    public Score score() {
        return cards.toMixHand().score();
    }

    private Result compare(Score playerScore) {
        if (score().lessThan(playerScore)) {
            return Result.LOSS;
        }
        if (score().moreThan(playerScore)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public boolean isHit() {
        return cards.score().lessThan(new Score(17));
    }
}
