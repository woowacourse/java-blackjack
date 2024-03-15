package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Deck {
    private final List<Card> cards;

    public Deck(ShuffleStrategy shuffleStrategy) {
        cards = new ArrayList<>(shuffleStrategy.shuffle(createAllCards()));
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
        return cards.remove(cards.size() - 1);
    }
}
