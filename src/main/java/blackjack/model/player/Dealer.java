package blackjack.model.player;

import blackjack.model.Card;
import blackjack.model.Cards;
import blackjack.model.Result;
import blackjack.model.Score;
import java.util.List;

public class Dealer extends Player {

    public Dealer(Card card1, Card card2, Card... cards) {
        super("딜러", List.of(card1), new Cards(card1, card2, cards));
    }

    public Result match(Cards cards) {
        Score playerScore = cards.bestScore();
        if (playerScore.isBust()) {
            return Result.WIN;
        }
        if (score().isBust()) {
            return Result.LOSE;
        }
        return compare(playerScore);
    }

    private Result compare(Score playerScore) {
        if (score().lessThan(playerScore)) {
            return Result.LOSE;
        }
        if (score().moreThan(playerScore)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public boolean isHittable() {
        return cards.softHandScore().lessThan(new Score(17));
    }
}
