package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;

import java.util.List;

public class Challenger extends Player {

    public Challenger(final Cards cards, final Name name) {
        super(cards, name);
    }

    public Result getChallengerResult(final Dealer dealer) {
        if (isBust() || (!dealer.isBust() && this.getScore() < dealer.getScore())) {
            return Result.LOSE;
        }
        if (dealer.isBust() || this.getScore() > dealer.getScore()) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    @Override
    public List<Card> getInitCards() {
        return cards.getList();
    }
}
