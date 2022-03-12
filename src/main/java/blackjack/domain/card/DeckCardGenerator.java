package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DeckCardGenerator {

    public Stack<Card> generate() {
        List<Card> cards = makeCards();
        Collections.shuffle(cards);
        Stack<Card> deck = new Stack<>();
        deck.addAll(cards);
        return deck;
    }

    private List<Card> makeCards() {
        List<Card> cards = new ArrayList<>();
        Type.getTypeValues()
                .forEach(type -> makeCardByScore(cards, type));
        return cards;
    }

    private void makeCardByScore(final List<Card> cards, final Type type) {
        Score.getScoreValues().stream()
                .map(score -> new Card(type, score))
                .forEach(cards::add);
    }
}
