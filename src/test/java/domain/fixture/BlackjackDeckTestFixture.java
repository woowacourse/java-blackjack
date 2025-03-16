package domain.fixture;

import domain.card.Deck;
import domain.card.TrumpCard;
import domain.card.strategy.BlackjackDrawStrategy;
import domain.card.strategy.TestDrawStrategy;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackDeckTestFixture {
    public static Deck createSequentialDeck(List<TrumpCard>... orders) {
        List<TrumpCard> drawOrder = toDrawOrder(orders);
        return new Deck(new TestDrawStrategy(new LinkedList<>(drawOrder)));
    }

    public static Deck createRandomDeck() {
        return new Deck(new BlackjackDrawStrategy());
    }

    private static List<TrumpCard> toDrawOrder(List<TrumpCard>... orders) {
        return Arrays.stream(orders)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
