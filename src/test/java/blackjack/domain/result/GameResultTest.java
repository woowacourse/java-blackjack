package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameResultTest {
    private Dealer dealer;
    private Players players;

    @BeforeEach
    void setUp() {
        dealer = Dealer.create();
        players = Players.of("그니, 무늬, 포비");
    }

    @Test
    void of() {
        assertThat(GameResult.of(dealer, players)).isNotNull();
    }
}