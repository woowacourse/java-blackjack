package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class RandomCardGenerator implements CardGenerator {

    @Override
    public Stack<Card> generate() {
        Stack<Card> deck = makeDeck();
        Collections.shuffle(deck);
        return deck;
    }

    private Stack<Card> makeDeck() {
        Stack<Card> deck = new Stack<>();
        for (Type type : Type.getTypeValues()) {
            makeCardByScore(deck, type);
        }
        return deck;
    }

    private void makeCardByScore(final Stack<Card> cards, final Type type) {
        for (Score score : Score.getScoreValues()) {
            cards.add(new Card(type, score));
        }
    }
}
