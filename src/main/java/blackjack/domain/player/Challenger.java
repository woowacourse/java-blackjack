package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;

import java.util.List;

public class Challenger extends Player {
    private boolean isBust;

    public Challenger(final Cards cards, final Name name) {
        super(cards, name);
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

    @Override
    public List<Card> getInitCards() {
        return cards.getList();
    }
}
