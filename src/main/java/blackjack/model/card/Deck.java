package blackjack.model.card;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class Deck {

    private final Queue<Card> cards;

    private Deck(Queue<Card> cards) {
        this.cards = cards;
    }

    public static Deck createStandardDeck(CardShuffler cardShuffler) {
        List<Card> cards = CardsFactory.createStandardDeckCards();
        return shuffleAndCreateDeck(cards, cardShuffler);
    }

    public static Deck createDeckByCards(List<Card> cards, CardShuffler cardShuffler) {
        return shuffleAndCreateDeck(new ArrayList<>(cards), cardShuffler);
    }

    private static Deck shuffleAndCreateDeck(List<Card> cards, CardShuffler cardShuffler) {
        cardShuffler.shuffle(cards);
        return new Deck(new LinkedList<>(cards));
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("더이상 뽑을 카드가 없습니다.");
        }
        return cards.poll();
    }

    public int size() {
        return cards.size();
    }
}
