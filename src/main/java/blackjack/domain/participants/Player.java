package blackjack.domain.participants;


import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Player {
    private final String name;
    private final Cards cards;

    public Player(String name, Cards cards) {
        this.name = name.trim();
        this.cards = cards;
    }

    public void take(Card card1, Card card2) {
        this.cards.take(card1, card2);
    }

    public void additionalTake(Card card) {
        this.cards.additionalTake(card);
    }

    public boolean canTake() {
        return cards.canTake();
    }

    public int calculateMaxScore() {
        return cards.calculateMaxScore();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public Cards getCards() {
        return cards;
    }

    public List<Card> retrieveCards() {
        return cards.getCards();
    }

    public String getName() {
        return name;
    }
}
