import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.player.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.ErrorMessage;

class NameTest {
    @Nested
    @DisplayName("constructor(): ")
    class Constructor {

        @ParameterizedTest
        @DisplayName("이름이 2~7자가 아닐경우 예외를 반환한다.")
        @CsvSource({
                "밥",
                "12345678",
        })
        void name(String name) {
            assertThatThrownBy(() -> new Name(name))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.PLAYER_NAME.getMessage());
        }
    }

}
