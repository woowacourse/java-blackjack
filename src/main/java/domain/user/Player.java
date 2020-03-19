package domain.user;

import domain.card.Cards;
import domain.result.Result;
import utils.StringUtils;

public class Player extends User {
    private Name name;
    private BettingMoney bettingMoney;
    private Result result;

    public Player(String name,int bettingMoney) {
        StringUtils.checkNameNullAndEmpty(name);
        this.name = new Name(name);
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    @Override
    public boolean isReceiveAble(){
        return isSmallerThan(Cards.BLACKJACK_SCORE);
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getName() {
        return name.getName();
    }

    public Result getResult() {
        return result;
    }

    public double getBettingMoney() {
        return bettingMoney.getBettingMoney();
    }
}
