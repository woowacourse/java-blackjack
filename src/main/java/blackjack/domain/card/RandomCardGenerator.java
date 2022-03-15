package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class RandomCardGenerator implements CardGenerator {

    @Override
    public Stack<Card> generate() {
        List<Card> cards = makeCards();
        Collections.shuffle(cards);
        Stack<Card> deck = new Stack<>();
        deck.addAll(cards);
        return deck;
    }

    private List<Card> makeCards() {
        List<Card> cards = new ArrayList<>();
        for (Type type : Type.getTypeValues()) {
            makeCardByScore(cards, type);
        }
        return cards;
    }

    private void makeCardByScore(final List<Card> cards, final Type type) {
        for (Score score : Score.getScoreValues()) {
            cards.add(new Card(type, score));
        }
    }
}
