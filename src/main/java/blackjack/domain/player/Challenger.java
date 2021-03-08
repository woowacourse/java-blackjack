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
        if (isLossCondition(dealer)) {
            return Result.LOSE;
        }
        if (isWinCondition(dealer)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public boolean isLossCondition(Dealer dealer) {
        return this.isBust() || scoreLessThanDealer(dealer);
    }

    public boolean isWinCondition(Dealer dealer) {
        return dealer.isBust() || scoreGreaterThanDealer(dealer);
    }

    public boolean scoreLessThanDealer(Dealer dealer) {
        return (!dealer.isBust() && this.getScore() < dealer.getScore());
    }

    public boolean scoreGreaterThanDealer(Dealer dealer) {
        return this.getScore() > dealer.getScore();
    }

    @Override
    public List<Card> getInitCards() {
        return cards.getCardsList();
    }
}
