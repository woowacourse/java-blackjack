package model.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void 정상_작동() {
        Player player = new Player(new PlayerName("player1"), null);

        assertThat(player.getCurrentHand().name()).isEqualTo("player");
    }

    @Test
    public void 동일한_플레이어_검증_정상_작동() {
        Player player1 = new Player(new PlayerName("player"), null);
        Player player2 = new Player(new PlayerName("player"), null);

        assertThat(player1.equals(player2)).isTrue();
    }

    @Test
    public void 검증_전략_정상_작동() {
        NameValidator nameValidator = (String name) -> {
            if(name.equals("딜러")) {
                throw new IllegalArgumentException(ErrorMessage.NO_PLAYER_NAME_DEALER.getMessage());
            }
        };

        assertThatThrownBy(() -> new Player(new PlayerName("테스트"), nameValidator)).isExactlyInstanceOf(IllegalArgumentException.class).hasMessage(ErrorMessage.NO_PLAYER_NAME_DEALER.getMessage());
    }
}
