package blackjack.model.player;

import blackjack.model.Card;
import blackjack.model.Cards;
import blackjack.model.Result;
import blackjack.model.Score;
import java.util.List;

public class Dealer extends Player {

    public Dealer(List<Card> cards) {
        super("딜러", new Cards(cards));
    }

    public Result match(Gamer gamer) {
        if (gamer.score().isBust()) {
            return Result.WIN;
        }
        if (score().isBust()) {
            return Result.LOSE;
        }
        return compare(gamer);
    }

    private Result compare(Gamer gamer) {
        if (isDealerWinCase(gamer)) {
            return Result.WIN;
        }
        if (isDealerLoseCase(gamer)) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    private boolean isDealerWinCase(Gamer gamer) {
        return score().moreThan(gamer.score()) || isBlackjack() && !gamer.isBlackjack();
    }

    private boolean isDealerLoseCase(Gamer gamer) {
        return score().lessThan(gamer.score()) || !isBlackjack() && gamer.isBlackjack();
    }

    @Override
    public List<Card> openCards() {
        return List.of(cards.getEachCard().get(0));
    }

    public boolean isHittable() {
        return cards.bestScore().lessThan(new Score(17));
    }
}
