package blackjack.fixture;

import blackjack.domain.participant.Player;
import blackjack.dto.PlayerInfo;

public class PlayerFixture {

    public static Player playerChoco() {
        return new Player(PlayerInfo.of("choco", "10000"));
    }

    public static Player playerClover() {
        return new Player(PlayerInfo.of("clover", "20000"));
    }
}
