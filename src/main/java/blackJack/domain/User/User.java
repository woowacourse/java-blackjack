package blackJack.domain.User;

import blackJack.domain.Card.Card;
import blackJack.domain.Card.Cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class User {

    public static final int WINNING_SCORE = 21;
    protected String name;
    protected Cards cards;
    protected BettingMoney bettingMoney;

    public User(String name) {
        this.name = name;
        this.cards = new Cards(new ArrayList<>());
    }

    public boolean isBlackJack() {
        return cards.isOnlyTwoCards() && cards.calculateScore() == WINNING_SCORE;
    }

    public boolean isBurst() {
        return this.getScore() > WINNING_SCORE;
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
        return cards.getCards();
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public int getBettingMoney() {
        return bettingMoney.getMoney();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(cards, user.cards) && Objects.equals(bettingMoney, user.bettingMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards, bettingMoney);
    }
}
