package blackJack.domain.User;

import blackJack.domain.Result;

public class Player extends User {

    private static final int PLAYER_ADD_CARD_LIMIT = 21;

    public Player(String name, Integer money) {
        super(name);
        bettingMoney = new BettingMoney(money);
    }

    public boolean isPossibleToAdd() {
        if(this.getScore() < PLAYER_ADD_CARD_LIMIT){
            return true;
        }
        return false;
    }

    public Result judgeByBlackjack() {
        if (isBlackJack()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }
}

