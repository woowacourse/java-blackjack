package blackjack.domain.player;

import static blackjack.domain.ResultTypeNew.DRAW;
import static blackjack.domain.ResultTypeNew.LOSS;
import static blackjack.domain.ResultTypeNew.NONE;
import static blackjack.domain.ResultTypeNew.WIN_NOT_WITH_BLACKJACK;
import static blackjack.domain.ResultTypeNew.WIN_WITH_BLACKJACK;

import blackjack.domain.BettingMoney;
import blackjack.domain.ResultType;
import blackjack.domain.ResultTypeNew;
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

    private ResultTypeNew getResultNew(Dealer dealer) {
        ResultTypeNew lossResult = getLossResult(dealer);
        if (lossResult != NONE) {
            return lossResult;
        }
        ResultTypeNew drawResult = getDrawResult(dealer);
        if (drawResult != NONE) {
            return drawResult;
        }
        return getWinResult(dealer);
    }

    private ResultTypeNew getLossResult(Dealer dealer) {
        if (isBust()) {
            return LOSS;
        }
        if (isNobodyBust(dealer)) {
            return getNobodyBustResult(dealer);
        }
        return NONE;
    }

    private ResultTypeNew getNobodyBustResult(Dealer dealer) {
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

    private ResultTypeNew getDrawResult(Dealer dealer) {
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

    private ResultTypeNew getWinResult(Dealer dealer) {
        if (isBlackJack() && !dealer.isBlackJack()) {
            return WIN_WITH_BLACKJACK;
        }
        return WIN_NOT_WITH_BLACKJACK;
    }

    public int getProfit(Dealer dealer) {
        return bettingMoney.getProfit(getResultNew(dealer));
    }
}
