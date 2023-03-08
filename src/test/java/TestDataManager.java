import domain.BlackjackGame;
import domain.Dealer;
import domain.DeckFactory;
import domain.Participant;
import domain.Participants;
import domain.Player;
import domain.ParticipantName;
import domain.PlayerNames;
import java.util.List;

public class TestDataManager {

    /**
     * pobi와 crong, royce가 참가한 BlackjackGame을 반환합니다.
     *
     * @return BlackjackGame
     */
    public static BlackjackGame getShuffledBlackjackGame() {
        Participants participants = Participants.from(PlayerNames.from(List.of("pobi", "crong", "royce")));
        return BlackjackGame.from(participants, DeckFactory.getShuffledDeck());
    }

    public static Participant getPlayerWithName(String name) {
        return Player.from(new ParticipantName(name));
    }

    public static Participant getDealer() {
        return new Dealer();
    }
}
