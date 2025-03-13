package domain.fixture;

import domain.BlackjackGame;
import domain.ParticipantName;
import domain.TrumpCard;
import java.util.List;

public class BlackjackGameTestFixture {
    public static BlackjackGame createTestGame(List<String> names, List<TrumpCard>... cards) {
        return new BlackjackGame(toParticipantNames(names), BlackjackDeckTestFixture.createSequentialDeck(cards));
    }

    private static List<ParticipantName> toParticipantNames(List<String> names) {
        return names.stream()
                .map(ParticipantName::new)
                .toList();
    }
}
