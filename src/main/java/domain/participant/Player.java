package domain.participant;

import domain.enums.Result;

public class Player extends Participant {

    private final Name name;

    public Player(String name) {
        super();
        this.name = new Name(name);
    }

    public Result calculateResult(int dealerScore, boolean dealerBust) {
        int playerScore = cardBoard.calculateScore();

        if (cardBoard.isBust() || (playerScore < dealerScore && !dealerBust)) {
            return Result.LOSE;
        }
        if (playerScore == dealerScore) {
            return Result.DRAW;
        }
        return Result.WIN;
    }

    public String getName() {
        return name.getName();
    }
}
