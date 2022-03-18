package blackJack.domain.User;

import blackJack.domain.Card.Card;
import blackJack.domain.Card.Cards;

import java.util.ArrayList;
import java.util.List;

public abstract class User {

    public static final int WINNING_LIMIT = 21;
    protected String name;
    protected Cards cards;
    protected BettingMoney bettingMoney;

    public User(String name) {
        this.name = name;
        this.cards = new Cards(new ArrayList<>());
    }

    public boolean isBlackJack() {
        if (cards.isOnlyTwoCards() && cards.calculateScore() == WINNING_LIMIT) {
            return true;
        }
        return false;
    }

    public boolean isBurst() {
        return this.getScore() > WINNING_LIMIT;
    }


    public void requestCard(Card card) {
        this.cards.add(card);
    }

    public void dealCard(Card card) {
        this.cards.add(card);
    }

    public abstract boolean isPossibleToAdd();

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getDeck();
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public int getBettingMoney() {
        return bettingMoney.getMoney();
    }
}
