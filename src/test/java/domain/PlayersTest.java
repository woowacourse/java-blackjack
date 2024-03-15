package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static domain.Players.NAMES_SIZE_INVALID_MESSAGE;
import static domain.Players.NAME_DUPLICATE_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @DisplayName("중복되는 이름은 허용하지 않는다.")
    @Test
    void validateNotDuplicated_success() {
        List<String> playerNames = List.of("위브", "산초");

        assertThatCode(() -> new Players(playerNames))
                .doesNotThrowAnyException();
    }

    @DisplayName("중복되는 이름이 들어오면 예외가 발생한다.")
    @Test
    void validateNotDuplicated_fail() {
        List<String> playerNames = List.of("산초", "산초");
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NAME_DUPLICATE_MESSAGE);
    }

    @DisplayName("1명 이상 10명 이하면 예외를 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource("createValidNames")
    void validateNamesSize(List<String> names) {
        assertThatCode(() -> new Players(names))
                .doesNotThrowAnyException();
    }

    static Stream<List<String>> createValidNames() {
        return Stream.of(
                List.of("산초"),
                List.of("A", "B", "C", "D", "E", "F", "G", "H", "I")
        );
    }

    @DisplayName("1명 미만 10명 초과이면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("createInValidNames")
    void validateNamesSizeFail(List<String> names) {
        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NAMES_SIZE_INVALID_MESSAGE);
    }

    static Stream<List<String>> createInValidNames() {
        return Stream.of(
                List.of(),
                List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K")
        );
    }
}
