package domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CardDeck {
    private final Deque<Card> cardDeck = new ArrayDeque<>();

    public CardDeck(ShuffleStrategy shuffleStrategy) {
        List<Card> shuffledCards = shuffleStrategy.shuffle(createCards());
        addAllCards(shuffledCards);
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
            cards.addAll(generateCardsBySuit(suit));
        }

        return cards;
    }

    private List<Card> generateCardsBySuit(TrumpSuit suit) {
        List<Card> suitCards = new ArrayList<>();
        for (TrumpNumber number : TrumpNumber.values()) {
            suitCards.add(new Card(suit, number));
        }

        return suitCards;
    }
}
