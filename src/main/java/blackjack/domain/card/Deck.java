package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;

public class Deck {

    private final Stack<Card> deck;

    public Deck() {
        deck = init();
    }

    private static Stack<Card> init() {
        Stack<Card> deck = new Stack<>();
        List<Card> cards = new ArrayList<>();

        Type.getTypeValues()
                .forEach(type -> makeCardByScore(cards, type));
        Collections.shuffle(cards);

        deck.addAll(cards);
        return deck;
    }

    private static void makeCardByScore(List<Card> cards, Type type) {
        Score.getScoreValues().stream()
                .map(score -> new Card(type, score))
                .forEach(cards::add);
    }

    public Card draw() {
        return deck.pop();
    }

    public boolean containCard(Card card) {
        return deck.contains(card);
    }
}
