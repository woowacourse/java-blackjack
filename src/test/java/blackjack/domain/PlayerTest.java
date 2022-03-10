package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("invalidParameters")
    @DisplayName("플레이어 생성 오류 테스트")
    void playerInvalidTest(List<String> playerNames, String testName) {
        assertThatThrownBy(() -> Player.of(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
                Arguments.of(List.of("pobi", "ash", ""), "빈문자 입력"),
                Arguments.of(List.of("pobiash", "pobi"), "6글자 초과 입력")
        );
    }

    @Test
    void test() {
        System.out.println(CardShape.CLUB);
    }
}
