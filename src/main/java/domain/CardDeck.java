package domain;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private final List<TrumpCard> cardDeck = new ArrayList<>();

    public CardDeck() {
        this.cardDeck.addAll(TrumpCard.cardDeckCaching());
    }

    public TrumpCard drawCard() {
        if (cardDeck.isEmpty()) {
            throw new IllegalArgumentException("카드가 다 떨어졌습니다");
        }
        return cardDeck.removeFirst();
    }

    public TrumpCard getCard(int index) {
        return cardDeck.get(index);
    }

    public List<TrumpCard> getCardDeck() {
        return new ArrayList<>(cardDeck);
    }
}
