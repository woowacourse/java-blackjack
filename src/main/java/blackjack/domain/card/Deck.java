package blackjack.domain.card;

import java.util.*;
<<<<<<< HEAD
import java.util.stream.IntStream;

public class Deck {

    protected static final int INIT_DISTRIBUTE_SIZE = 2;

    private final Stack<Card> deck;

    public Deck() {
        deck = init();
    }

    private static Stack<Card> init() {
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

    public List<Card> initDistributeCard() {
        List<Card> cards = new ArrayList<>();
        IntStream.range(0, INIT_DISTRIBUTE_SIZE)
                .forEach(i -> cards.add(draw()));
        return cards;
=======

public class Deck {

    private final Stack<Card> deck;

    public Deck(DeckGenerator deckGenerator) {
        deck = deckGenerator.generate();
>>>>>>> step1
    }

    public Card draw() {
        return deck.pop();
    }
}
