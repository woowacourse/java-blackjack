package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

public class Deck {
    private final Deque<Card> cards;

    public Deck(ShuffleStrategy shuffleStrategy) {
        cards = new ArrayDeque<>(shuffleStrategy.shuffle(createAllCards()));
    }

    private static List<Card> createAllCards() {
        return Arrays.stream(CardSuit.values()).flatMap(cardSymbol ->
                        Arrays.stream(CardScore.values()).map(cardScore ->
                                new Card(cardSymbol, cardScore)))
                .toList();
    }

    public List<Card> draw(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> draw())
                .toList();
    }

    private Card draw() {
        return cards.removeLast();
    }
}
