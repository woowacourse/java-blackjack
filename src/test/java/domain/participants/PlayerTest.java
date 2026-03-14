package domain.participants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.bet.Betting;
import domain.hitStrategy.UntilBustHitStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {
    @Nested
    @DisplayName("constructor(): ")
    class Constructor {

        @ParameterizedTest
        @DisplayName("이름이 2~7자가 아닐경우 예외를 반환한다.")
        @CsvSource({
                "밥",
                "12345678",
        })
        void player(String name) {
            assertThatThrownBy(
                    () -> new Player(name, new Betting(0),
                            new UntilBustHitStrategy()))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

}
