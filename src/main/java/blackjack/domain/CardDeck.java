package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private static final int HEAD_INDEX = 0;

    private List<Card> cardDeck;

    public CardDeck(List<Card> cards) {
        cardDeck = new ArrayList<>(cards);
    }

    public Card getOneCard() {
        return cardDeck.remove(HEAD_INDEX);
    }
}
