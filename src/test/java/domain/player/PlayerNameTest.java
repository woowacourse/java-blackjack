package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PlayerNameTest {
    @DisplayName("정상 입력시 예외 미 발생")
    @ParameterizedTest(name = "name : {0}")
    @ValueSource(strings = {"a", "아벨입니다"})
    void inputNoException(String name) {
        assertThatNoException()
                .isThrownBy(() -> new PlayerName(name));
    }
    
    @DisplayName("이름 길이의 범위를 벗어난 경우 예외 처리")
    @ParameterizedTest(name = "name : {0}")
    @ValueSource(strings = {"", "아벨입니다a"})
    void nameOutOfLengthException(String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PlayerName(name))
                .withMessage("참가자의 이름 길이 범위는 1~5를 벗어날 수 없습니다.");
    }
}