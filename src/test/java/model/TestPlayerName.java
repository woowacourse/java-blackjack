package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import org.junit.jupiter.api.Test;

public class TestPlayerName {

    @Test
    public void 정상_동작() {
        PlayerName playerName = new PlayerName("a");

        assertThat(playerName.value()).isEqualTo("a");
    }

    @Test
    public void 플레이어_이름_빈값_예외() {
        assertThatThrownBy(() -> new PlayerName(""))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INPUT_IS_BLANK.getMessage());

    }
}
