package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class RandomDeck implements Deck {
    private static final RandomDeck INSTANCE = new RandomDeck();
    private static final Random RANDOM = new Random();

    private final List<Card> cards;

    private RandomDeck() {
        this.cards = Arrays.stream(Number.values())
                .flatMap(RandomDeck::createAllShapeCardsForNumber)
                .toList();
    }

    private static Stream<Card> createAllShapeCardsForNumber(Number number) {
        return Arrays.stream(Shape.values())
                .map(shape -> new Card(number, shape));
    }

    public static RandomDeck getInstance() {
        return INSTANCE;
    }

    @Override
    public Card drawCard() {
        int orderOfCard = RANDOM.nextInt(cards.size());
        return cards.get(orderOfCard);
    }
}
