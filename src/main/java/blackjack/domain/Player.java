package blackjack.domain;

import blackjack.util.BlackJackConstant;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    public Result getResult(int dealerScore) {
        if (dealerScore != BlackJackConstant.BLACKJACK_SCORE &&
                this.getScore() == BlackJackConstant.BLACKJACK_SCORE){
            return Result.BLACKJACK;
        }

        if (this.getScore() > dealerScore) {
            return Result.WIN;
        }

        if (this.getScore() < dealerScore || this.isBust()) {
            return Result.LOSE;
        }

        return Result.DRAW;
    }
}
