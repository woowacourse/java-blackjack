package blackjack.model;

import blackjack.model.cards.OwnCards;
import java.util.List;

public class Dealer extends Player {

    public static final Score HIT_BOUNDARY = new Score(17);

    public Dealer(Card card1, Card card2, Card... cards) {
        super("딜러", List.of(card1), new OwnCards(card1, card2, cards));
    }

    public Result match(Gamer gamer) {
        if (gamer.isBust()) {
            return Result.WIN;
        } else if (isBust()) {
            return Result.LOSS;
        }
        return compare(score(), gamer.score());
    }

    private Result compare(Score dealerScore, Score playerScore) {
        if (dealerScore.lessThan(playerScore)) {
            return Result.LOSS;
        } else if (dealerScore.moreThan(playerScore)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public boolean isHittable() {
        return cards.maxScore().lessThan(HIT_BOUNDARY);
    }
}
