package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Deck {

    public static final int CARD_TOTAL_SIZE = 52;
    private static final int FIRST_CARD = 0;
    private static final int MIN_CARD_SIZE = 1;
    private static final Deck deck = new Deck();
    private static List<Card> cards;

    private Deck() {
        List<Card> createdCards = CardFactory.of();
        validate(createdCards);
        cards = createdCards;
    }

    public static Deck getInstance() {
        return deck;
    }

    private static void validate(List<Card> cards) {
        if (cards.size() != CARD_TOTAL_SIZE) {
            throw new IllegalArgumentException(String.format("카드의 개수는 총 %d개여야 합니다.", CARD_TOTAL_SIZE));
        }
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.size() < MIN_CARD_SIZE) {
            throw new IndexOutOfBoundsException("뽑을 수 있는 카드가 없습니다.");
        }
        return cards.remove(FIRST_CARD);
    }
}
