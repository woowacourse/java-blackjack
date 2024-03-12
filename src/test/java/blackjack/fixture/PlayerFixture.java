package blackjack.fixture;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerFixture {

    public static Player playerChoco(final Dealer dealer) {
        return new Player("choco", dealer);
    }

    public static Player playerClover(final Dealer dealer) {
        return new Player("clover", dealer);
    }
}
