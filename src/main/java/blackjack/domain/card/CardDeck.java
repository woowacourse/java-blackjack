package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private static final String EMPTY_CARD_DECK = "[ERROR] 카드 덤프가 비어 있습니다!";

    private final List<Card> cardDeck;

    private CardDeck(List<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public static CardDeck createShuffledDeck() {
        List<Card> cards = initializeCardDeck();
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }

    private static List<Card> initializeCardDeck() {
        List<Card> cards = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        return cards;
    }

    public Card drawCard() {
        if (cardDeck.isEmpty()) {
            throw new IllegalStateException(EMPTY_CARD_DECK);
        }
        return cardDeck.removeFirst();
    }
}
