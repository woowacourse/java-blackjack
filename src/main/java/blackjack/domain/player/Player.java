package blackjack.domain.player;

import blackjack.domain.result.Result;

public class Player extends Participant {

    private static final int LIMIT_SCORE = 20;
    private static final int DEFAULT_BET_MONEY = 1000;

    private final BetMoney betMoney;

    public Player() {
        this("null", DEFAULT_BET_MONEY);
    }

    public Player(String name, long betMoney) {
        super(name);
        this.betMoney = new BetMoney(betMoney);
    }

    public void compareWithDealer(Dealer dealer) {
        dealer.compare(this);
    }

    public boolean canDrawOneMore(){
        return getScore() <= LIMIT_SCORE;
    }

    public boolean isLose(Result result) {
        return result == Result.LOSE;
    }

    public float profit(Result result) {
        if (isBlackjack()) {
            return betMoney.getBlackjackBetMoney();
        }

        if (isLose(result)) {
            return -1 * betMoney.getBetMoney();
        }

        return betMoney.getBetMoney();
    }
}
