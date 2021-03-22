package blackjack.domain;

import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackManagerTest {

    private Players players;

    @BeforeEach
    void setUp() {
        List<Player> playerList = Arrays.asList(
                new Player(new Name("pobi"), Money.of(1)),
                new Player(new Name("jason"), Money.of(1))
        );
        players = Players.createPlayers(playerList);
    }

    @Test
    @DisplayName("각 플레이어가 초기 2장씩 소지한다.")
    void testAllPlayersGetTwoCards() {
        BlackjackManager blackjackManager = new BlackjackManager(players);
        blackjackManager.initGame();

        assertThat(players.toList()
                .stream()
                .filter(player -> player.toHandList().size() == 2)
                .count())
                .isEqualTo(2);
    }
}
