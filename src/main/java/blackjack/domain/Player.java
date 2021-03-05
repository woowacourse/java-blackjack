package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.util.StringUtil;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    public ResultType decisionGameWinOrLose(int dealerScore) {
        if (this.getScore() > dealerScore) {
            return ResultType.WIN;
        }

        if (this.getScore() < dealerScore || this.getScore() == Card.BUST) {
            return ResultType.LOSE;
        }

        return ResultType.DRAW;
    }
}
