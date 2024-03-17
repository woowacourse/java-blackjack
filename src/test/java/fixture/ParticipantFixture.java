package fixture;

import domain.participant.BettingAmount;
import domain.participant.Name;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.Arrays;

public class ParticipantFixture {
    public static Player 플레이어(String name) { // todo 수정
        return new Player(new Name(name), new BettingAmount(1));
    }

    public static Participants 참가자들(String... playerNames) { // todo 수정
        return new Participants(Arrays.stream(playerNames)
                .map(name -> new Player(new Name(name), new BettingAmount(1)))
                .toList());
    }
}
