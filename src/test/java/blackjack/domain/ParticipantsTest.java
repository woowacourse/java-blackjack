package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ParticipantsTest {

    @Test
    @DisplayName("참가자의 이름이 중복될 경우 예외를 발생시킨다.")
    void throwExceptionWhenDuplicate() {
        List<String> names = List.of("엘리", "배카라", "배카라");
        assertThatThrownBy(() -> new Participants(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자의 이름은 중복될 수 없습니다.");
    }

    @ParameterizedTest(name = "[{index}] 참가자 {0}")
    @MethodSource("generateNames")
    @DisplayName("참가자의 인원이 2명~8명 사이가 아닐 경우 예외를 발생시킨다.")
    void throwExceptionWhenOutOfBounds(List<String> names) {
        assertThatThrownBy(() -> new Participants(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참자가 인원은 2명~8명 사이여야합니다.");
    }

    static Stream<Arguments> generateNames() {
        return Stream.of(
                Arguments.of(List.of("a")),
                Arguments.of(List.of("a", "b", "c", "d", "e", "f", "g", "h", "i")),
                Arguments.of(List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"))
        );
    }
}
