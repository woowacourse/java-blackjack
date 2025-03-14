package domain.fixture;

import domain.card.TrumpCard;
import domain.game.BlackjackGame;
import domain.participant.ParticipantName;
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
