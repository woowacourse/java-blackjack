package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;

public class Challenger extends Player {
    private final Name name;
    private boolean isBust;

    public Challenger(final Cards cards, final Name name) {
        super(cards);
        this.name = name;
        this.isBust = false;
    }

    public Result getChallengerResult(final Dealer dealer) {
        if (this.getScore() > dealer.getScore()) {
            return Result.WIN;
        }
        if (this.getScore() < dealer.getScore()) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }
}
