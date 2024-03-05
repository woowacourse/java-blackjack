package player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    private static final int MAX_NAME_LENGTH = 10;

    @ParameterizedTest
    @ValueSource(strings = {"hogiAndFriends", "polabearIsWhite", "hiImHogeeee"})
    @DisplayName("이름은 최대 개수를 넘으면 안된다.")
    void isOverMaxLength(String inputNames) {
        Assertions.assertThatThrownBy(() -> new Name(inputNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 " + MAX_NAME_LENGTH + "글자를 넘을 수 없습니다.");
    }

    @DisplayName("이름에 빈 값이 들어가면 안된다.")
    @Test
    void isBlankName() {
        Assertions.assertThatThrownBy(() -> new Name(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 빈값이 될 수 없습니다.");
    }
}