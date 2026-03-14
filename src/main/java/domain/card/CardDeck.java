package domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CardDeck {
    private final Deque<Card> cardDeck = new ArrayDeque<>();

    public CardDeck(ShuffleStrategy shuffleStrategy) {
        List<Card> cards = createCards();
        shuffleStrategy.shuffle(cards);
        addAllCards(cards);
    }

    public Card draw() {
        return cardDeck.pop();
    }

    private void addAllCards(List<Card> cards) {
        cardDeck.addAll(cards);
    }

    private List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        for (TrumpSuit suit : TrumpSuit.values()) {
            for (TrumpNumber number : TrumpNumber.values()) {
                cards.add(new Card(suit, number));
            }
        }

        return cards;
    }
}
