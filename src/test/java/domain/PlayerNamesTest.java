package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.PlayerNames.NAME_DUPLICATE_MESSAGE;

class PlayerNamesTest {

    @DisplayName("중복되는 이름은 허용하지 않는다.")
    @Test
    void validateNotDuplicated_success() {
        List<String> playerNames = List.of("위브", "산초");

        Assertions.assertThatCode(() -> new PlayerNames(playerNames))
                .doesNotThrowAnyException();
    }

    @DisplayName("중복되는 이름이 들어오면 예외가 발생한다.")
    @Test
    void validateNotDuplicated_fail() {
        List<String> playerNames = List.of("산초", "산초");
        Assertions.assertThatThrownBy(() -> new PlayerNames(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NAME_DUPLICATE_MESSAGE);
    }
}
