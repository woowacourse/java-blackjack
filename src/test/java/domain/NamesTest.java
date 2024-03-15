package domain;

import domain.participant.Names;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
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
        Assertions.assertThatThrownBy(() -> Names.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참여자 이름은 중복될 수 없습니다.");
    }

    @DisplayName("총 참여자 수가 2이상 8이하이면 참여자를 생성한다.")
    @ParameterizedTest
    @MethodSource("validPlayersSizeParameterProvider")
    void validPlayersSize(final List<String> names) {
        Assertions.assertThatCode(() -> Names.from(names))
                .doesNotThrowAnyException();
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
}
