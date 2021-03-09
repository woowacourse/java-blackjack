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
        if (isBlackJack()) {
            return blackJackResult(dealer);
        }
        if (isChallengerLose(dealer)) {
            return Result.LOSE;
        }
        if (isChallengerWin(dealer)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    private Result blackJackResult(final Dealer dealer) {
        if (dealer.isBlackJack()) {
            return Result.DRAW;
        }
        return Result.BLACKJACK;
    }


    private boolean isChallengerLose(final Dealer dealer) {
        return isBust() || (!dealer.isBust() && this.getScore() < dealer.getScore());
    }

    private boolean isChallengerWin(final Dealer dealer) {
        return dealer.isBust() || this.getScore() > dealer.getScore();
    }

    @Override
    public List<Card> getInitCards() {
        return hand.getList();
    }
}
