package blackjack.domain.gambler;

import static blackjack.domain.fixture.NameFixture.createNames;
import static blackjack.view.ErrorMessage.INVALID_PLAYER_COUNT;
import static blackjack.view.ErrorMessage.NAME_CANNOT_BE_DUPLICATED;
import static blackjack.view.ErrorMessage.NAME_CANNOT_BE_EQUAL_DEALER_NAME;
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
        List<Name> names = List.of(new Name("딜러"));

        // when
        // then
        assertThatThrownBy(() -> new Names(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NAME_CANNOT_BE_EQUAL_DEALER_NAME.getMessage());
    }

    @DisplayName("플레이어의 이름이 중복되면 예외를 발생한다")
    @Test
    void validateDuplicatedNames() {
        // given
        List<Name> names = List.of(new Name("레오"), new Name("레오"));

        // when
        // then
        assertThatThrownBy(() -> new Names(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NAME_CANNOT_BE_DUPLICATED.getMessage());
    }

    @DisplayName("플레이어 수가 기준 미만이거나 초과이면 예외를 발생한다")
    @MethodSource("returnInvalidCountPlayerNames")
    @ParameterizedTest
    void validatePlayersCount(List<Name> playerNames) {
        // when
        // then
        assertThatThrownBy(() -> new Names(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PLAYER_COUNT.getMessage());
    }

    private static Stream<Arguments> returnInvalidCountPlayerNames() {
        return Stream.of(Arguments.arguments(createNames()),
                Arguments.arguments(createNames("레오", "듀이", "비타", "몽이", "꾹이", "플린트", "젠슨")));
    }
}
