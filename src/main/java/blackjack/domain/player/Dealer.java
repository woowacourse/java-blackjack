package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;

public class Dealer extends Player {
    public Dealer(Cards cards) {
        super(cards);
    }

    public Result getChallengerResult(final Challenger challenger) {
        if (challenger.getScore() > this.getScore()) {
            return Result.WIN;
        }
        if (challenger.getScore() < this.getScore()) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }
}
