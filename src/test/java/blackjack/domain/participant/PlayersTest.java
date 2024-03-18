package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.participant.Players.*;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @DisplayName("중복되는 이름은 허용하지 않는다.")
    @Test
    void validateNotDuplicated_success() {
        List<String> names = List.of("위브", "산초");
        List<Integer> bets = List.of(10, 20);

        assertThatCode(() -> new Players(names, bets))
                .doesNotThrowAnyException();
    }

    @DisplayName("중복되는 이름이 들어오면 예외가 발생한다.")
    @Test
    void validateNotDuplicated_fail() {
        List<String> names = List.of("산초", "산초");
        List<Integer> bets = List.of(10, 20);

        assertThatThrownBy(() -> new Players(names, bets))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NAME_DUPLICATE_MESSAGE);
    }

    @DisplayName("1명 이상 10명 이하면 예외를 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource("createValidNames")
    void validateNamesSize(List<String> names) {
        List<Integer> bets = Stream.generate(() -> 10)
                .limit(names.size())
                .toList();

        assertThatCode(() -> new Players(names, bets))
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
        List<Integer> bets = Stream.generate(() -> 10)
                .limit(names.size())
                .toList();

        assertThatThrownBy(() -> new Players(names, bets))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NAMES_SIZE_INVALID_MESSAGE);
    }

    static Stream<List<String>> createInValidNames() {
        return Stream.of(
                List.of(),
                List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K")
        );
    }

    @DisplayName("사용자 이름의 수와 베팅 금액의 수가 다르면 예외가 발생한다.")
    @Test
    void validatePlayerSizeBetSizeEqual() {
        List<String> names = List.of("산초", "아서");
        List<Integer> bets = List.of(10);

        assertThatThrownBy(() -> new Players(names, bets))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NAMES_SIZE_BETS_SIZE_NOT_EQUAL_MESSAGE);
    }
}
