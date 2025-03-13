package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Cards;
import java.util.List;

public class Dealer {
    private final Cards ownedCards;

    private Dealer() {
        this.ownedCards = Cards.of();
    }

    public static Dealer of() {
        return new Dealer();
    }

    public void receive(Card card) {
        ownedCards.add(card);
    }

    public int getScore() {
        return ownedCards.calculateScore();
    }

    public int getCardCount() {
        return ownedCards.getSize();
    }

    public List<Card> getOwnedCards() {
        return ownedCards.getCards();
    }
}
