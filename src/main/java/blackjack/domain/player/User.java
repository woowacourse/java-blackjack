package blackjack.domain.player;

import blackjack.domain.BettingMoney;
import blackjack.domain.ResultType;
import blackjack.domain.UserDrawContinue;

public class User extends AbstractPlayer {
    private boolean isDrawStop = false;
    private final BettingMoney bettingMoney;

    public User(String name) {
        super(name);
        bettingMoney = new BettingMoney();
    }

    public User(String name, int bettingMoney) {
        super(name);
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    public boolean isDrawStop() {
        return isDrawStop;
    }

    @Override
    public boolean isCanDraw() {
        return !(isDrawStop() || isBust());
    }

    public boolean isDrawContinue(UserDrawContinue userDrawContinue) {
        if (userDrawContinue.isContinue()) {
            return true;
        }
        isDrawStop = true;
        return false;
    }

    public ResultType getResult(Dealer dealer) {
        ResultType bustResult = getBustResult(dealer);
        if (bustResult != null) {
            return bustResult;
        }
        ResultType blackJackResult = getBlackJackResult(dealer);
        if (blackJackResult != null) {
            return blackJackResult;
        }
        return getRestResult(dealer);
    }

    private ResultType getBustResult(Dealer dealer) {
        if (isBust()) {
            return ResultType.LOSS;
        }
        if (dealer.isBust()) {
            return ResultType.WIN;
        }
        return null;
    }

    private ResultType getBlackJackResult(Dealer dealer) {
        if (isBlackJack() && dealer.isBlackJack()) {
            return ResultType.DRAW;
        }
        if (isBlackJack() && !dealer.isBlackJack()) {
            return ResultType.WIN;
        }
        if (!isBlackJack() && dealer.isBlackJack()) {
            return ResultType.LOSS;
        }
        return null;
    }

    private ResultType getRestResult(Dealer dealer) {
        if (getScore() > dealer.getScore()) {
            return ResultType.WIN;
        }
        if (getScore() == dealer.getScore()) {
            return ResultType.DRAW;
        }
        return ResultType.LOSS;
    }
}
