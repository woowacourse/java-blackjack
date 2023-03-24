package domain.card;

import java.util.*;

public class CardDeck {
    private static final List<Card> INIT_CARD_DECK = makeCards();
    private final Deque<Card> cardDeck;

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

    public CardDeck() {
        Collections.shuffle(INIT_CARD_DECK);
        this.cardDeck = new ArrayDeque<>(INIT_CARD_DECK);
    }

    public Card pickCard() {
        return this.cardDeck.pop();
    }
}
