package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int CARD_TOTAL_SIZE = 48;
    static List<Card> cards = new ArrayList<>();

    public static void init(List<Card> newCards) {
        validate(newCards);
        cards = newCards;
    }

    private static void validate(List<Card> cards) {
        if (cards.size() != CARD_TOTAL_SIZE) {
            throw new IllegalArgumentException("카드의 개수는 총 48개여야 합니다.");
        }
    }
}
