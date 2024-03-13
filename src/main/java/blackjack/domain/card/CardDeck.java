package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private static final List<Card> INITIAL_CARD_DECK = CardFactory.create();

    private List<Card> cardDeck;

    public CardDeck() {
        this.cardDeck = initializeCardDeck();
    }

    private List<Card> initializeCardDeck() {
        return new ArrayList<>(INITIAL_CARD_DECK);
    }

    public Card draw() {
        if (cardDeck.isEmpty()) {
            this.cardDeck = initializeCardDeck();
        }
        return cardDeck.remove(cardDeck.size() - 1);
    }
}
