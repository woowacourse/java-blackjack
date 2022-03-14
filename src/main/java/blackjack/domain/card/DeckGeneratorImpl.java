package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DeckGeneratorImpl implements DeckGenerator {

    @Override
    public Stack<Card> generate() {
        Stack<Card> deck = new Stack<>();
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
