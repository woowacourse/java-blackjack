package blackjack.domain.gambler;

import static blackjack.domain.fixture.NameFixture.createNames;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NamesTest {
    @DisplayName("플레이어의 이름이 딜러와 동일하면 예외를 발생한다")
    @Test
    void validateEqualsDealerName() {
        // given
        List<Name> names = createNames("딜러");

        // when
        // then
        assertThatThrownBy(() -> new Names(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'딜러'라는 이름은 사용이 불가능 하다.");
    }

    @DisplayName("플레이어의 이름이 중복되면 예외를 발생한다")
    @Test
    void validateDuplicatedNames() {
        // given
        List<Name> names = createNames("레오", "레오");

        // when
        // then
        assertThatThrownBy(() -> new Names(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 중복 불가하다.");
    }

    @DisplayName("플레이어 수가 기준 미만이거나 초과이면 예외를 발생한다")
    @MethodSource("returnInvalidCountPlayerNames")
    @ParameterizedTest
    void validatePlayersCount(List<Name> playerNames) {
        // when
        // then
        assertThatThrownBy(() -> new Names(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 명수는 최소 1명, 최대 6명까지다.");
    }

    private static Stream<Arguments> returnInvalidCountPlayerNames() {
        return Stream.of(Arguments.arguments(createNames()),
                Arguments.arguments(createNames("레오", "듀이", "비타", "몽이", "꾹이", "플린트", "젠슨")));
    }
}
