package model.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void 정상_작동() {
        Player player = new Player("player");

        assertThat(player.getCurrentHand().name()).isEqualTo("player");
    }

    @Test
    public void 동일한_플레이어_검증_정상_작동() {
        Player player1 = new Player("player");
        Player player2 = new Player("player");

        assertThat(player1.equals(player2)).isTrue();
    }
}
