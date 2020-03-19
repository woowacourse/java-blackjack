package model.user;

import java.util.List;
import model.user.money.BettingMoney;
import model.card.Card;

public class Player extends BlackJackGameUser {
    private final BettingMoney bettingMoney;

    public Player(String name, BettingMoney bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

    public Player(String name, List<Card> cards) {
        super(name, cards);
        this.bettingMoney = new BettingMoney("100");
    }

    public double multiplyBettingMoney(double factor) {
        return bettingMoney.multiplyBettingMoney(factor);
    }
}