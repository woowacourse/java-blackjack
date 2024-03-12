package blackjack.domain.card;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomDeck implements Deck {
    private static final RandomDeck INSTANCE = new RandomDeck();
    private static final Random RANDOM = new Random();

    private final List<Card> cards;

    private RandomDeck() {
        this.cards = Stream.of(Number.values())
                .flatMap(number -> Stream.of(Shape.values())
                        .map(shape -> new Card(number, shape)))
                .collect(Collectors.toList());
    }

    public static RandomDeck getInstance() {
        return INSTANCE;
    }

    @Override
    public Card drawCard() {
        return cards.get(RANDOM.nextInt(cards.size()));
    }
}
