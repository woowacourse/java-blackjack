package domain.card.deck;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private final List<Card> cardDeck;

    public CardDeck(List<Card> cards) {
        this.cardDeck = new ArrayList<>(cards);
    }

    public Card draw() {
        if (cardDeck.isEmpty()) {
            throw new IllegalStateException("카드덱의 카드를 모두 소진했습니다.");
        }
        return cardDeck.remove(lastIndexOfCard());
    }

    private int lastIndexOfCard() {
        return cardDeck.size() - 1;
    }
}
