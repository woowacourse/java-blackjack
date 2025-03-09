package domain.fixture;

import domain.BlackjackDeck;
import domain.BlackjackDeckGenerator;
import domain.TrumpCard;
import domain.strategy.BlackjackDrawStrategy;
import domain.strategy.TestDrawStrategy;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackDeckTestFixture {
    public static BlackjackDeck createSequentialDeck(List<TrumpCard>... orders) {
        List<TrumpCard> drawOrder = toDrawOrder(orders);
        return BlackjackDeckGenerator.generateDeck(new TestDrawStrategy(new LinkedList<>(drawOrder)));
    }

    public static BlackjackDeck createRandomDeck() {
        return BlackjackDeckGenerator.generateDeck(new BlackjackDrawStrategy());
    }

    private static List<TrumpCard> toDrawOrder(List<TrumpCard>... orders) {
        return Arrays.stream(orders)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
