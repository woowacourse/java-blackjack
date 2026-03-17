package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    private Players players;

    @BeforeEach
    void setUp() {
        Player player1 = new Player("player1", new Betting(10000));
        Player player2 = new Player("player2", new Betting(20000));
        this.players = new Players(List.of(player1, player2));
    }

    @DisplayName("Player가 없으면 allBurst는 false를 반환한다.")
    @Test
    void 플레이어가_없으면_allBurst는_false_반환() {
        Players players = new Players(List.of());

        boolean isAllBurst = players.isAllPlayerBurst();

        assertThat(isAllBurst).isEqualTo(false);
    }


}