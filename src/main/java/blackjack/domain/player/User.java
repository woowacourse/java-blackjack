package blackjack.domain.player;

import static blackjack.domain.ResultType.DRAW;
import static blackjack.domain.ResultType.LOSS;
import static blackjack.domain.ResultType.NONE;
import static blackjack.domain.ResultType.WIN_NOT_WITH_BLACKJACK;
import static blackjack.domain.ResultType.WIN_WITH_BLACKJACK;

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

    public int getProfit(Dealer dealer) {
        return bettingMoney.getProfit(getResultNew(dealer));
    }

    private ResultType getResultNew(Dealer dealer) {
        ResultType lossResult = getLossResult(dealer);
        if (lossResult != NONE) {
            return lossResult;
        }
        ResultType drawResult = getDrawResult(dealer);
        if (drawResult != NONE) {
            return drawResult;
        }
        return getWinResult(dealer);
    }

    private ResultType getLossResult(Dealer dealer) {
        if (isBust()) {
            return LOSS;
        }
        if (isNobodyBust(dealer)) {
            return getNobodyBustResult(dealer);
        }
        return NONE;
    }

    private ResultType getNobodyBustResult(Dealer dealer) {
        if (!isBlackJack() && dealer.isBlackJack()) {
            return LOSS;
        }
        if (getScore() < dealer.getScore()) {
            return LOSS;
        }
        return NONE;
    }

    private boolean isNobodyBust(Dealer dealer) {
        return !isBust() && !dealer.isBust();
    }

    private ResultType getDrawResult(Dealer dealer) {
        if (isBlackJack() && dealer.isBlackJack()) {
            return DRAW;
        }
        if (isNobodyBlackJack(dealer) && isSameScore(dealer)) {
            return DRAW;
        }
        return NONE;
    }

    private boolean isNobodyBlackJack(Dealer dealer) {
        return !isBlackJack() && !dealer.isBlackJack();
    }

    private boolean isSameScore(Dealer dealer) {
        return getScore() == dealer.getScore();
    }

    private ResultType getWinResult(Dealer dealer) {
        if (isBlackJack() && !dealer.isBlackJack()) {
            return WIN_WITH_BLACKJACK;
        }
        return WIN_NOT_WITH_BLACKJACK;
    }
}
