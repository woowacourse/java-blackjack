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
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Players players = new Players(List.of(player1, player2));
        this.players = players;
    }

    @DisplayName("총 배팅 금액을 구한다.")
    @Test
    void 총_배팅_금액_계산(){
        players.getPlayer(0).betMoney(10000);
        players.getPlayer(1).betMoney(20000);

        int total = players.getTotalBettingScore();

        assertThat(total).isEqualTo(30000);
    }




}