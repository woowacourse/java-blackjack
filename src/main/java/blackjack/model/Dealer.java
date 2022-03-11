package blackjack.model;

import java.util.List;

public class Dealer extends Player {

    public Dealer(Card card1, Card card2, Card... cards) {
        super("딜러", List.of(card1), new Cards(card1, card2, cards));
    }

    public Result match(Gamer gamer) {
        if (gamer.isBust()) {
            return Result.WIN;
        }
        if (isBust()) {
            return Result.LOSS;
        }
        return compare(score(), gamer.score());
    }

    private Result compare(Score dealerScore, Score playerScore) {
        if (dealerScore.lessThan(playerScore)) {
            return Result.LOSS;
        }
        if (dealerScore.moreThan(playerScore)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public boolean isHittable() {
        return cards.maxScore().lessThan(new Score(17));
    }
}
