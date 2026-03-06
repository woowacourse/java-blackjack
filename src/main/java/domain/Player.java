package domain;

import domain.enums.Result;

public class Player extends Participant {

    private final String name;
    private Result result;

    public Player(String name) {
        super();
        this.name = name;
        result = Result.UNDECIDED;
    }

    public String getName() {
        return name;
    }

    public Result calculateResult(int dealerScore, boolean dealerBurst) {
        int playerScore = cardBoard.calculateScore();

        if (cardBoard.isBurst() || (playerScore < dealerScore && !dealerBurst)) {
            return result = Result.LOSE;
        }
        if (playerScore == dealerScore) {
            return result = Result.DRAW;
        }
        return result = Result.WIN;
    }

    public Result getResult() {
        return result;
    }
}
