package domain.fixture;

import domain.card.TrumpCard;
import domain.game.BlackjackGame;
import java.util.Collections;
import java.util.List;

public class BlackjackGameTestFixture {
    public static BlackjackGame createTestGame(List<String> names, List<TrumpCard>... cards) {
        List<Integer> bets = Collections.nCopies(names.size(), 1_000);
        return new BlackjackGame(names, bets, BlackjackDeckTestFixture.createSequentialDeck(cards));
    }
}
