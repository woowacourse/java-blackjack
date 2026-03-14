package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import exception.GameException;
import org.junit.jupiter.api.Test;

public class PlayerNameTest {

    @Test
    public void 정상_동작() {
        PlayerName playerName = new PlayerName("a");

        assertThat(playerName.value()).isEqualTo("a");
    }

    @Test
    public void 플레이어_이름_빈값_예외() {
        assertThatThrownBy(() -> new PlayerName(""))
                .isExactlyInstanceOf(GameException.class)
                .hasMessage("빈 값을 입력하셨습니다.");
    }
}
