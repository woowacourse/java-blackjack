package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayersTest {

    @DisplayName("플레이어는 두 명 이상이어야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    void validPlayersTest(int value) {
        final List<Name> names = new ArrayList<>();
        final List<Integer> bets = new ArrayList<>();
        for (int i = 0; i < value; i++) {
            names.add(Name.of("hi" + i));
            bets.add(10000 * (i + 1));
        }
        assertDoesNotThrow(() -> Players.create(names, bets));
    }

    @DisplayName("플레이어는 한 명 이하이면 예외가 발생한다.")
    @Test
    void invalidPlayersTest() {
        final List<Name> names = Collections.emptyList();
        assertThatThrownBy(() -> Players.create(names, Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1명 이상일 때 게임을 실행할 수 있습니다.");
    }

    @DisplayName("플레이어의 이름이 중복되면 예외가 발생한다.")
    @Test
    void duplicatedPlayersTest() {
        final List<Name> names = List.of(
                Name.of("hi"),
                Name.of("hi")
        );
        assertThatThrownBy(() -> Players.create(names, Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름은 사용할 수 없습니다.");
    }
}
