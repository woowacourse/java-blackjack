package domain;

import static domain.Name.DEALER_NAME_MESSAGE;
import static domain.PlayerNames.NAMES_SIZE_INVALID_MESSAGE;
import static domain.PlayerNames.NAME_DUPLICATE_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerNamesTest {

    @DisplayName("중복되는 이름은 허용하지 않는다.")
    @Test
    void validateNotDuplicated_success() {
        List<String> playerNames = List.of("위브", "산초");

        assertThatCode(() -> new PlayerNames(playerNames))
                .doesNotThrowAnyException();
    }

    @DisplayName("중복되는 이름이 들어오면 예외가 발생한다.")
    @Test
    void validateNotDuplicated_fail() {
        List<String> playerNames = List.of("산초", "산초");
        assertThatThrownBy(() -> new PlayerNames(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NAME_DUPLICATE_MESSAGE);
    }

    static Stream<Arguments> createValidNames() {
        return Stream.of(
                Arguments.of(
                        List.of("산초"),
                        List.of("A", "B", "C", "D", "E", "F", "G", "H", "I")
                )
        );
    }

    @DisplayName("1명 이상 10명 이하면 예외를 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource("createValidNames")
    void validateNamesSize(List<String> names) {
        assertThatCode(() -> new PlayerNames(names))
                .doesNotThrowAnyException();
    }

    static Stream<Arguments> createInValidNames() {
        return Stream.of(
                Arguments.of(
                        List.of(),
                        List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
                )
        );
    }


    @DisplayName("1명 이상 10명 이하면 예외를 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource("createInValidNames")
    void validateNamesSizeFail(List<String> names) {
        assertThatThrownBy(() -> new PlayerNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NAMES_SIZE_INVALID_MESSAGE);
    }

    @DisplayName("플레이어 이름은 딜러가 될 수 없습니다.")
    @Test
    void validateNotDealerName() {
        assertThatThrownBy(() -> new PlayerNames(List.of("위브", "산초", "딜러")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DEALER_NAME_MESSAGE);
    }
}
