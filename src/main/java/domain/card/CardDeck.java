package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private static final int FIRST = 0;

    private List<Card> cardDeck;

    public CardDeck() {
        List<Card> cardsDeck = Card.getCards();
        Collections.shuffle(cardsDeck);
        this.cardDeck = cardsDeck;
    }

    public Card drawCard() {
        validateCardsDeck();
        return cardDeck.remove(FIRST);
    }

    private void validateCardsDeck() {
        if (this.cardDeck.isEmpty()) {
            throw new IllegalArgumentException("카드를 모두 소모하였습니다. 프로그램이 종료됩니다.");
        }
    }

    public List<Card> initialDraw() {
        return new ArrayList<>(Arrays.asList(drawCard(), drawCard()));
    }
}
