package domain;

import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int FIRST = 0;
    private static final int MIN_CARDS_DECK = 1;

    private List<Card> cardsDeck;

    public Cards() {
        this.cardsDeck = Card.getCards();
    }

    public Card pop() {
        validateCardsDeck();
        Collections.shuffle(cardsDeck);
        return cardsDeck.remove(FIRST);
    }

    private void validateCardsDeck() {
        if (this.cardsDeck.size() < MIN_CARDS_DECK) {
            throw new IllegalArgumentException("카드를 모두 소모하였습니다. 프로그램이 종료됩니다.");
        }
    }
}
