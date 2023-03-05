package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayersTest {

    @DisplayName("플레이어는 두 명 이상이어야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    void validPlayersTest(int value) {
        final List<Name> names = new ArrayList<>();
        for (int i = 0; i < value; i++) {
            names.add(Name.of("hi" + i));
        }
        assertDoesNotThrow(() -> Players.create(names));
    }

    @DisplayName("플레이어는 한 명 이하이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void invalidPlayersTest(int value) {
        final List<Name> names = new ArrayList<>();
        for (int i = 0; i < value; i++) {
            names.add(Name.of("hi" + i));
        }
        assertThatThrownBy(() -> Players.create(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("두 명 이상일 때 게임을 실행할 수 있습니다.");
    }
}
