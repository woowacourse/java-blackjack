package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.game.ScoreRule;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class User {
    protected final String name;
    protected final List<Card> cards;
    protected Money money;

    public User(String name, Money money) {
        this.name = name;
        this.cards = new LinkedList<>();
        this.money = money;
    }

    public void receiveInitialCards(List<Card> initialCards) {
        this.cards.addAll(Objects.requireNonNull(initialCards));
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public int getTotalScore() {
        return ScoreRule.calculateTotalScore(cards);
    }

    public String getName() {
        return this.name;
    }

    public void changeMoneyByRate(double rate) {
        this.money = this.money.multiply(rate);
    }

    public Money getMoney() {
        return money;
    }

    public boolean isBusted() {
        return ScoreRule.isBusted(cards);
    }

    public boolean isBlackJack() {
        return ScoreRule.isBlackjack(cards);
    }

    public abstract List<Card> getInitialCards();

    public int compareScore(User user) {
        return this.getTotalScore() - user.getTotalScore();
    }

    public List<Card> getCards() {
        return cards;
    }
}
