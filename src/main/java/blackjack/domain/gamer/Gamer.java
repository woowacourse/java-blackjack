package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Gamer {

    private final String name;
    protected final Cards cards = new Cards();

    protected Gamer(String name, Card... cards) {
        this.name = name;
        addStartingCards(cards);
    }

    private void addStartingCards(Card... startingCards) {
        for (Card card : startingCards) {
            cards.add(card);
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getCardCount() {
        return cards.count();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public int getSumOfCards() {
        return cards.sum();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public abstract boolean canReceiveAdditionalCards();

    public String getName() {
        return name;
    }
}
