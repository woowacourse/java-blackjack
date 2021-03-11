package blackjack.domain;

import blackjack.state.State;
import blackjack.util.BlackJackConstant;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    public Result getResult(Dealer dealer) {
        if (!dealer.isBlackJack() && this.isBlackJack()){
            return Result.BLACKJACK;
        }

        if (this.getScore() < dealer.getScore() ||
                this.state.cards().isBust()) {
            return Result.LOSE;
        }

        if (this.getScore() > dealer.getScore()) {
            return Result.WIN;
        }

        return Result.DRAW;
    }
}
