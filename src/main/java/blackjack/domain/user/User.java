package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.game.GameRule;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class User {
    protected final String name;
    protected final List<Card> cards;

    public User(String name) {
        this.name = name;
        this.cards = new LinkedList<>();
    }

    public void receiveInitialCards(List<Card> initialCards) {
        this.cards.addAll(Objects.requireNonNull(initialCards));
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public int getTotalScore() {
        return GameRule.calculateTotalScore(cards);
    }

    public String getName() {
        return this.name;
    }

    public boolean isBusted() {
        return GameRule.isBusted(cards);
    }

    public boolean isBlackJack() {
        return GameRule.isBlackjack(cards);
    }

    public abstract List<Card> getInitialCards();

    public List<Card> getCards() {
        return cards;
    }
}
