package model.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import org.junit.jupiter.api.Test;

public class PlayerNameTest {
    @Test
    public void 정상_이름_작동() {
        String name = "player";
        PlayerName playerName = new PlayerName(name);
        assertThat(playerName.getName()).isEqualTo(name);
    }

    @Test
    public void 빈_이름_예외() {
        assertThatThrownBy(() -> new PlayerName(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NO_EMPTY_NAME.getMessage());

        assertThatThrownBy(() -> new PlayerName(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NO_EMPTY_NAME.getMessage());
    }
}
