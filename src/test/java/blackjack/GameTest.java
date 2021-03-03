package blackjack;

import blackjack.participant.Dealer;
import blackjack.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {
    List<String> names = Arrays.asList("wannte","bepoz");
    Game game;

    @BeforeEach
    void setUp() {
        game = Game.of(names);
    }

    @Test
    void createGame() {
        assertThat(game.getPlayers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드 두장 셋업")
    void setUpTwoCards() {
        game.setUpTwoCards();
        for (Player player : game.getPlayers()) {
            assertThat(player.getCards().size()).isEqualTo(2);
        }
        Dealer dealer = game.getDealer();
        assertThat(dealer.getCards().size()).isEqualTo(2);
    }
}