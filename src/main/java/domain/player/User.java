package domain.player;

import domain.game.Money;

public class User extends Player {
    private Money bettingMoney;

    public User(final String name) {
        super(name);
        this.bettingMoney = new Money("0");
    }

    public boolean isWin(final Player opponentPlayer) {
        boolean dealerBust = opponentPlayer.isBust();
        boolean hasMoreScore = (getScore() > opponentPlayer.getScore());

        return isNotBust() && (dealerBust || hasMoreScore);
    }

    public void addBettingMoney(Money money) {
        bettingMoney = bettingMoney.addMoney(money.getMoney());
    }

    public Money getBettingMoney() {
        return bettingMoney;
    }
}
