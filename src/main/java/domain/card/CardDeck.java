package domain.card;

import java.util.Collections;
import java.util.List;

public class CardDeck {
    private static final int FIRST = 0;

    private List<Card> cardsDeck;

    public CardDeck() {
        List<Card> cardsDeck = Card.getCards();
        Collections.shuffle(cardsDeck);
        this.cardsDeck = cardsDeck;
    }

    public Card hit() {
        validateCardsDeck();
        return cardsDeck.remove(FIRST);
    }

    private void validateCardsDeck() {
        if (this.cardsDeck.isEmpty()) {
            throw new IllegalArgumentException("카드를 모두 소모하였습니다. 프로그램이 종료됩니다.");
        }
    }
}
