package domain.fixture;

import domain.card.Deck;
import domain.card.TrumpCard;
import domain.card.strategy.BlackjackDrawStrategy;
import domain.card.strategy.TestDrawStrategy;
import java.util.LinkedList;
import java.util.List;

public class BlackjackDeckTestFixture {
    public static Deck createSequentialDeck(List<TrumpCard> drawOrder) {
        return new Deck(new TestDrawStrategy(new LinkedList<>(drawOrder)));
    }

    public static Deck createRandomDeck() {
        return new Deck(new BlackjackDrawStrategy());
    }
}
