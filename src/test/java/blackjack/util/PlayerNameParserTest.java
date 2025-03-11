package blackjack.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerNameParserTest {

    @ParameterizedTest
    @MethodSource("playerNameParsingCase")
    @DisplayName("플레이어 입력을 분리해 반환한다")
    void 플레이어_입력을_분리해_반환한다(String nameInput, List<String> excepted) {
        List<String> result = PlayerNameParser.parseNames(nameInput);
        assertThat(result)
                .isEqualTo(excepted);
    }

    @MethodSource
    private static Stream<Arguments> playerNameParsingCase() {
        return Stream.of(
                Arguments.of("비타,레오,꾹이", List.of("비타", "레오", "꾹이")),
                Arguments.of("비타, 레오, 꾹이", List.of("비타", "레오", "꾹이")),
                Arguments.of("비타,레오", List.of("비타", "레오"))
        );
    }
}
