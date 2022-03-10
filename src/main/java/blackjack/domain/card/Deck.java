package blackjack.domain.card;

import java.util.*;
import java.util.stream.IntStream;

public class Deck {

    protected static final int INIT_DISTRIBUTE_SIZE = 2;

    private final Stack<Card> deck;

    public Deck() {
        deck = init();
    }

    private static Stack<Card> init() {
        List<Card> cards = makeCards();
        Collections.shuffle(cards);
        Stack<Card> deck = new Stack<>();
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
        Score.getScoreValues().stream()
                .map(score -> new Card(type, score))
                .forEach(cards::add);
    }

    public List<Card> initDistributeCard() {
        List<Card> cards = new ArrayList<>();
        IntStream.range(0, INIT_DISTRIBUTE_SIZE)
                .forEach(i -> cards.add(draw()));
        return cards;
    }

    public Card draw() {
        return deck.pop();
    }

    public boolean containCard(final Card card) {
        return deck.contains(card);
    }
}
