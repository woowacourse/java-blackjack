package blackjack.fixture;

import blackjack.domain.participant.Player;

public class PlayerFixture {

    public static Player playerChoco() {
        return new Player("choco");
    }

    public static Player playerClover() {
        return new Player("clover");
    }
}
