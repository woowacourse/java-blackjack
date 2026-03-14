package model.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void 정상_작동() {
        String name = "player1";
        Player player = new Player(new PlayerName(name));

        assertThat(player.getCurrentHand().name()).isEqualTo(name);
    }

    @Test
    public void 동일한_플레이어_검증_정상_작동() {
        PlayerName name = new PlayerName("player");

        Player player1 = new Player(name);
        Player player2 = new Player(name);
        Player player3 = new Player(new PlayerName("player2"));

        assertThat(player1.equals(player2)).isTrue();
        assertThat(player1.equals(player3)).isFalse();
    }
}
