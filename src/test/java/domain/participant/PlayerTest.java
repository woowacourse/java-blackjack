package domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @DisplayName("플레이어 이름 범위 넘어선 경우의 예외 테스트합니다.")
    @Test
    void 플레이어_이름_예외_테스트() {
        Assertions.assertThatThrownBy(
                () -> new Player("tony stark")
        );
    }

    @DisplayName("플레이어 정상 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"stark", "fizz", "스타크", "피즈"})
    void 플레이어_정상_생성_테스트(String name) {
        Player player = new Player(name);
        Assertions.assertThat(player.getName()).isEqualTo(name);
    }
}
