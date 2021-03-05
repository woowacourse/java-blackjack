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
        if (loseCondition(dealer)) {
            return Result.LOSE;
        }
        if (winCondition(dealer)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public boolean loseCondition(Dealer dealer){
        return isBust() || (!dealer.isBust() && this.getScore() < dealer.getScore());
    }

    public boolean winCondition(Dealer dealer){
        return dealer.isBust() || this.getScore() > dealer.getScore();
    }

    @Override
    public List<Card> getInitCards() {
        return cards.getList();
    }
}
