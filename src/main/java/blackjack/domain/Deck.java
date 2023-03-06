package blackjack.domain;

import java.util.*;

public class Deck {
    private static final String NO_REMAIN_CARD_ERROR_MESSAGE = "남은 카드가 없습니다.";

    private final Queue<Card> cards = new LinkedList<>();

    public Deck() {
        generateDeck();
    }

    private void generateDeck() {
        for (Suit suit : Suit.values()) {
            generateCardEachSuit(suit);
        }
        Collections.shuffle((List<?>) cards);
    }

    private void generateCardEachSuit(Suit suit) {
        for (Letter letter : Letter.values()) {
            cards.add(new Card(suit, letter));
        }
    }

    public Card getCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(NO_REMAIN_CARD_ERROR_MESSAGE);
        }
        return cards.poll();
    }

    public List<Card> getTwoCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(getCard());
        cards.add(getCard());
        return cards;
    }
}
