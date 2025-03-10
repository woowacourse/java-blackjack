package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Cards;
import java.util.List;

public class Dealer {
    private final CardDeck cardDeck;
    private final Cards ownedCards;

    private Dealer(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
        this.ownedCards = Cards.of();
    }

    public static Dealer of(CardDeck cardDeck) {
        return new Dealer(cardDeck);
    }

    public Card drawCard() {
        return cardDeck.popCard();
    }

    public void receive() {
        ownedCards.add(cardDeck.popCard());
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
