package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.gamer.Player;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("invalidParameters")
    @DisplayName("플레이어 생성 오류 테스트")
    void playerInvalidTest(String playerName, int bettingMoney, String testName) {
        assertThatThrownBy(() -> Player.of(playerName, BettingMoney.of(bettingMoney)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
                Arguments.of("", 10, "빈문자 입력"),
                Arguments.of("pobiash", 10, "6글자 초과 입력"),
                Arguments.of("pepper", 9, "베팅 최소 금액 미만 입력")
        );
    }
}
