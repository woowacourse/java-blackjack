package blackJack.domain.User;

import blackJack.domain.Result;

public class Player extends User {

    private static final int PLAYER_ADD_CARD_LIMIT = 21;

    public Player(String name) {
        super(name);
    }

    public boolean isPossibleToAdd() {
        return this.getScore() < PLAYER_ADD_CARD_LIMIT;
    }

    public Result judgeByBlackjack() {
        if (isBlackJack()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }
}

