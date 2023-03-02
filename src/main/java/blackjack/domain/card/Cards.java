package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int CARD_TOTAL_SIZE = 48;
    private static final int FIRST_CARD = 0;
    private static final int MIN_CARD_SIZE = 1;
    private static List<Card> cards = new ArrayList<>();

    public static void init(List<Card> newCards) {
        validate(newCards);
        cards = newCards;
    }

    private static void validate(List<Card> cards) {
        if (cards.size() != CARD_TOTAL_SIZE) {
            throw new IllegalArgumentException("카드의 개수는 총 48개여야 합니다.");
        }
    }

    public static void shuffleCards() {
        Collections.shuffle(cards);
    }

    public static Card giveFirstCard() {
        if (cards.size() < MIN_CARD_SIZE) {
            throw new IndexOutOfBoundsException("뽑을 수 있는 카드가 없습니다.");
        }
        return cards.remove(FIRST_CARD);
    }
}
