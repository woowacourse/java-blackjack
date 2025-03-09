package domain.fixture;

import domain.BlackjackGame;
import domain.Dealer;
import domain.TrumpCard;
import java.util.List;

public class BlackjackGameTestFixture {
    public static BlackjackGame createTestGame(List<String> names, List<TrumpCard>... cards) {
        return new BlackjackGame(names, BlackjackDeckTestFixture.createSequentialDeck(cards), new Dealer());
    }
}
