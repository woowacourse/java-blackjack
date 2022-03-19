package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public final class RandomGenerator implements CardGenerator {

    @Override
    public Deque<Card> generate() {
        Deque<Card> deck = new ArrayDeque<>();
        List<Card> cards = makeCards();
        Collections.shuffle(cards);
        deck.addAll(cards);
        return deck;
    }

    private static List<Card> makeCards() {
        List<Card> cards = new ArrayList<>();
        for (Type type : Type.getTypeValues()) {
            makeCardByScore(cards, type);
        }
        return cards;
    }

    private static void makeCardByScore(final List<Card> cards, final Type type) {
        for (Score score : Score.values()) {
            cards.add(new Card(type, score));
        }
    }
}
