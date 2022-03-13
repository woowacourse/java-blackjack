package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DeckGeneratorImpl implements DeckGenerator {

    @Override
    public Stack<Card> generate() {
        Stack<Card> deck = new Stack<>();
        List<Card> cards = makeCards();
        cardShuffle(cards);
        deck.addAll(cards);
        return deck;
    }

    private static List<Card> makeCards() {
        List<Card> cards = new ArrayList<>();
        Type.getTypeValues()
                .forEach(type -> makeCardByScore(cards, type));
        return cards;
    }

    private static void makeCardByScore(final List<Card> cards, final Type type) {
        Arrays.stream(Score.values())
                .map(score -> new Card(type, score))
                .forEach(cards::add);
    }

    private static void cardShuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
