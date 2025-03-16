package domain.fixture;

import domain.card.TrumpCard;
import domain.game.BlackjackGame;
import domain.participant.Bet;
import domain.participant.ParticipantName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGameTestFixture {
    public static BlackjackGame createTestGame(List<String> names, List<TrumpCard> cards) {
        Map<ParticipantName, Bet> playerBets = new HashMap<>();
        List<ParticipantName> participantNames = names.stream()
                .map(ParticipantName::new)
                .toList();
        for (ParticipantName participantName : participantNames) {
            playerBets.putIfAbsent(participantName, new Bet(10_000));
        }
        return new BlackjackGame(participantNames, playerBets, BlackjackDeckTestFixture.createSequentialDeck(cards));
    }
}
