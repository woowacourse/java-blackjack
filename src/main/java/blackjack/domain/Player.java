package blackjack.domain;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    public ResultType decisionGameWinOrLose(int dealerScore) {
        if (this.getScore() > dealerScore) {
            return ResultType.WIN;
        }

        if (this.getScore() < dealerScore || this.isBust()) {
            return ResultType.LOSE;
        }

        return ResultType.DRAW;
    }
}
