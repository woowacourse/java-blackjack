package domain;

import java.util.List;
import java.util.Stack;

public class CardDeck {
    private final Stack<Card> cardDeck;

    public CardDeck(List<Card> cards) {
        Stack<Card> cardDeck = new Stack<>();
        cardDeck.addAll(cards);
        this.cardDeck = cardDeck;
    }

    public void shuffle(CardShuffleStrategy cardShuffleStrategy) {
        cardShuffleStrategy.shuffle(cardDeck);
    }

    public Card draw() {
        if (cardDeck.empty()) {
            throw new IllegalArgumentException("카드덱의 카드를 모두 소진했습니다.");
        }
        return cardDeck.pop();
    }
}
