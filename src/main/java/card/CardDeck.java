package card;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private final List<Card> cardDeck = new ArrayList<>();

    public CardDeck() {
        this.cardDeck.addAll(Card.cardDeckCaching());
    }

    public Card drawCard() {
        if (cardDeck.isEmpty()) {
            throw new IllegalArgumentException("카드가 다 떨어졌습니다");
        }
        return cardDeck.removeFirst();
    }

    public List<Card> getCardDeck() {
        return new ArrayList<>(cardDeck);
    }
}
