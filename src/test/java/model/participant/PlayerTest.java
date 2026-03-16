package model.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import dto.status.PlayerName;
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
            if(name.equals("테스트")) {
                throw new IllegalArgumentException("테스트 이름을 사용할 수 없습니다.");
            }
        };

        assertThatThrownBy(() -> new Player(new PlayerName("테스트"), nameValidator)).isExactlyInstanceOf(IllegalArgumentException.class).hasMessage("테스트 이름을 사용할 수 없습니다.");
    }
}
