package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.PlayerErrorCode;
import exception.GameException;
import org.junit.jupiter.api.Test;

public class TestPlayer {
    @Test
    public void 정상_작동() {
        String name = "player1";
        Player player = new Player(new PlayerName(name), new BattingMoney("10000"));

        assertThat(player.getResult().name()).isEqualTo(name);
    }

    @Test
    public void 딜러_이름_예외() {
        assertThatThrownBy(() -> new Player(new PlayerName("딜러"), new BattingMoney("10000")))
                .isExactlyInstanceOf(GameException.class)
                .satisfies(e -> assertThat(((GameException) e).getErrorCode())
                        .isEqualTo(PlayerErrorCode.NO_PLAYER_NAME_DEALER));
    }

    @Test
    public void 동일한_플레이어_검증_정상_작동() {
        PlayerName name = new PlayerName("player");
        BattingMoney battingMoney = new BattingMoney("10000");

        Player player1 = new Player(name, battingMoney);
        Player player2 = new Player(name, battingMoney);
        Player player3 = new Player(new PlayerName("player2"), battingMoney);

        assertThat(player1.equals(player2)).isTrue();
        assertThat(player1.equals(player3)).isFalse();
    }
}
