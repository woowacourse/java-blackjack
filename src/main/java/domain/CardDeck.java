package domain;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private final List<TrumpCard> CARD_DECK = new ArrayList<>();

    public CardDeck() {
        this.CARD_DECK.addAll(TrumpCard.cardDeckCaching());
    }

    public TrumpCard drawCard() {
        if (CARD_DECK.isEmpty()) {
            throw new IllegalArgumentException("카드가 다 떨어졌습니다");
        }
        return CARD_DECK.removeFirst();
    }

    public TrumpCard getCard(int index) {
        return CARD_DECK.get(index);
    }

    public List<TrumpCard> getCardDeck() {
        return new ArrayList<>(CARD_DECK);
    }
}
