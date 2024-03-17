package blackjack.fixture;

import blackjack.domain.participant.Betting;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;

public class PlayerFixture {

    public static Player playerChoco() {
        return new Player(new Name("choco"), new Betting("10000"));
    }

    public static Player playerClover() {
        return new Player(new Name("clover"), new Betting("20000"));
    }
}
