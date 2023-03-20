package domain.card;

import java.util.*;

public class CardDeck {
    private static final int FIRST_CARD_INDEX_IN_DECK = 0;
    private static final List<Card> CARDDECK;

    static {
        CARDDECK = makeCards();
    }

    private static List<Card> makeCards() {
        List<Card> cards = new ArrayList<>();
        for (CardType cardType : CardType.values()) {
            addCards(cards, cardType);
        }
        return cards;
    }

    private static void addCards(List<Card> cards, CardType cardType) {
        for (CardValue cardValue : CardValue.values()) {
            cards.add(new Card(cardType, cardValue));
        }
    }


    public Card pickCard() {
        Collections.shuffle(CARDDECK);
        return CARDDECK.get(FIRST_CARD_INDEX_IN_DECK);
    }

    public List<Card> getCardDeck() {
        return CARDDECK;
    }
}
