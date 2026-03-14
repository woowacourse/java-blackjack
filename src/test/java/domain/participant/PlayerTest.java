package domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @DisplayName("플레이어 정상 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"tony", "fizz"})
    void 플레이어_이름_예외_테스트(String name) {
        Player player = new Player(name);

        Name playerName = player.getName();

        Assertions.assertThat(playerName).isEqualTo(new Name(name));
    }
}
