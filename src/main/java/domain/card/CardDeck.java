package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class CardDeck {
    private static final String NO_CARD_LEFT_MESSAGE = "모든 카드가 소진됐습니다. 게임을 재시작 해주세요.";

    private final static Queue<Card> cardDeck = new LinkedList<>();

    static {
        final List<Card> cards = new ArrayList<>();

        for (Face face : Face.values()) {
            init(face, cards);
        }

        Collections.shuffle(cards);

        cardDeck.addAll(cards);
    }

    private static void init(final Face face, final List<Card> cards) {
        for (final Suit suit : Suit.values()) {
            cards.add(new Card(suit, face));
        }
    }

    private CardDeck() {
    }

    public static Card draw() {
        if (cardDeck.isEmpty()) {
            throw new NoSuchElementException(NO_CARD_LEFT_MESSAGE);
        }
        return cardDeck.poll();
    }
}
