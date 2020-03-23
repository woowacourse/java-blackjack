package domain.user;

import domain.card.Cards;
import domain.result.Result;
import domain.result.ResultCalculator;
import utils.StringUtils;

public class Player extends User {
    private Name name;
    private BettingMoney bettingMoney;
    private Result result;

    public Player(String name, int bettingMoney) {
        StringUtils.checkNameNullAndEmpty(name);
        this.name = new Name(name);
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    @Override
    public boolean isReceiveAble() {
        return isSmallerThan(Cards.BLACKJACK_SCORE);
    }

    public Result compare(Dealer dealer) {
        if (ResultCalculator.isDraw(dealer.getTotalScore(), this.getTotalScore())) {
            return Result.DRAW;
        }
        if (ResultCalculator.isPlayerLose(dealer.getTotalScore(), this.getTotalScore())) {
            return Result.LOSE;
        }
        return Result.WIN;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Name getName() {
        return name;
    }

    public Result getResult() {
        return result;
    }

    public double getBettingMoney() {
        return bettingMoney.getBettingMoney();
    }
}
