package domain.participant;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.DuplicatePlayerNameException;
import exception.InvalidPlayersSizeException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NamesTest {

    @Test
    @DisplayName("참여자 이름 중복시 예외가 발생한다.")
    void duplicatePlayerName() {
        //given
        final List<String> names = List.of("redy", "redy");

        //when & then
        assertThatThrownBy(() -> Names.from(names))
                .isInstanceOf(DuplicatePlayerNameException.class);
    }

    @ParameterizedTest
    @DisplayName("참여자 이름에 공백이 있는 경우 제거하여 중복을 판단한다.")
    @MethodSource("duplicateBlankNameParameterProvider")
    void duplicateBlankName(final List<String> names) {
        assertThatThrownBy(() -> Names.from(names))
                .isInstanceOf(DuplicatePlayerNameException.class);
    }

    @DisplayName("총 참여자 수가 2이상 8이하이면 참여자를 생성한다.")
    @ParameterizedTest
    @MethodSource("validPlayersSizeParameterProvider")
    void validPlayersSize(final List<String> names) {
        assertThatCode(() -> Names.from(names))
                .doesNotThrowAnyException();
    }

    @DisplayName("총 참여자 수는 2이상 8이하가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidPlayersSizeParameterProvider")
    void invalidPlayersSize(final List<String> names) {
        assertThatThrownBy(() -> Names.from(names))
                .isInstanceOf(InvalidPlayersSizeException.class);
    }

    static Stream<Arguments> duplicateBlankNameParameterProvider() {
        return Stream.of(
                Arguments.of(List.of("a", "a ", "b")),
                Arguments.of(List.of(" a ", " a", "b", "c")),
                Arguments.of(List.of("a", " a"))
        );
    }

    static Stream<Arguments> validPlayersSizeParameterProvider() {
        return Stream.of(
                Arguments.of(
                        List.of("pobi", "jason")
                ),
                Arguments.of(
                        List.of("1", "2", "3", "4", "5", "6", "7", "8")
                )
        );
    }

    static Stream<Arguments> invalidPlayersSizeParameterProvider() {
        return Stream.of(
                Arguments.of(
                        List.of("pobi")
                ),
                Arguments.of(
                        List.of("1", "2", "3", "4", "5", "6", "7", "8", "9")
                )
        );
    }
}
