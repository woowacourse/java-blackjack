package domain.participant;

import domain.enums.Result;

public class Player extends Participant {

    private final String name;

    public Player(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Result calculateResult(int dealerScore, boolean dealerBurst) {
        int playerScore = cardBoard.calculateScore();

        if (cardBoard.isBust() || (playerScore < dealerScore && !dealerBurst)) {
            return Result.LOSE;
        }
        if (playerScore == dealerScore) {
            return Result.DRAW;
        }
        return Result.WIN;
    }
}
