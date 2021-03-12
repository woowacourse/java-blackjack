package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GameTest {
    final List<String> names = Arrays.asList("wannte", "bepoz");
    Game game;

    @BeforeEach
    void setUp() {
        game = Game.of(names);
    }

    @Test
    void createGame() {
        assertThat(game.getPlayers()).hasSize(2);
    }

    @Test
    @DisplayName("카드 두장 셋업")
    void setUpTwoCards() {
        game.startRound();
        for (Player player : game.getPlayers()) {
            assertThat(player.getCards()).hasSize(2);
        }
        Dealer dealer = game.getDealer();
        assertThat(dealer.getCards()).hasSize(2);
    }

    @Test
    void dealerHitUntilStay() {
        game.startRound();
        game.playDealerTurn();
        assertFalse(game.getDealer().shouldDraw());
    }
}