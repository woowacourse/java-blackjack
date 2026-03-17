package domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @DisplayName("플레이어는 전달받은 이름을 가진다.")
    @ParameterizedTest
    @ValueSource(strings = {"tony", "fizz"})
    void 플레이어_이름_전달받은_이름_동일(String name) {
        Name playerName = new Name(name);
        Player player = new Player(playerName);

        Name actualName = player.getName();

        Assertions.assertThat(actualName).isEqualTo(playerName);
    }
}
