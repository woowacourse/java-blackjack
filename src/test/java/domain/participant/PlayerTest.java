package domain.participant;

import static message.ErrorMessage.PLAYER_NAME_OUT_OF_RANGE;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @DisplayName("플레이어 이름의 길이가 1미만, 5초과인 경우 예외가 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"tony stark", "", "number"})
    void 플레이어_이름_예외_테스트(String name) {
        Assertions.assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PLAYER_NAME_OUT_OF_RANGE.getMessage());
    }

    @DisplayName("플레이어 이름이 1이상 5이하일 경우 정상 생성됩니다.")
    @ParameterizedTest
    @ValueSource(strings = {"stark", "fizz", "스타크", "피즈"})
    void 플레이어_정상_생성_테스트(String name) {
        Player player = new Player(name);
        Assertions.assertThat(player.getName()).isEqualTo(name);
    }
}
