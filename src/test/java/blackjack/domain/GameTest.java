package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {
    final List<String> names = Arrays.asList("wannte", "bepoz");
    Game game;

    @BeforeEach
    void setUp() {
        game = Game.of(new Players(names));
    }

    @Test
    void createGame() {
        assertThat(game.getPlayers()
                .size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드 두 장 세팅됐는지 확인")
    void setUpTwoCards() {
        for (Player player : game.getPlayers()) {
            assertThat(player.getCards().size()).isEqualTo(2);
        }
        Dealer dealer = game.getDealer();
        assertThat(dealer.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어에게 카드 지급 확인")
    void giveCardToPlayerTest() {
        Player player = game.getPlayers().get(0);
        game.giveCardToPlayer(player);
        assertThat(player.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러에게 카드 지급 확인")
    void giveCardToDealerTest() {
        Dealer dealer = game.getDealer();
        game.giveCardToDealer();
        assertThat(dealer.getCards().size()).isEqualTo(3);
    }
}