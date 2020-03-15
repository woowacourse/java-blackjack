package domain.card;

import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int FIRST = 0;

    private final List<Card> cardsDeck;

    public Cards() {
        this.cardsDeck = Card.getCards();
        Collections.shuffle(cardsDeck);
    }

    public Card giveCard() {
        validateCardsDeck();
        return cardsDeck.remove(FIRST);
    }

    private void validateCardsDeck() {
        if (this.cardsDeck.isEmpty()) {
            throw new IllegalArgumentException("카드를 모두 소모하였습니다. 프로그램이 종료됩니다.");
        }
    }
}
