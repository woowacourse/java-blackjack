package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class WinningTypeTest {

    static final int WIN_POINT = 21;
    static final int LOSE_POINT = 20;
    static final int BUST_POINT = 22;

    @ParameterizedTest
    @DisplayName("입력된 승패타입의 반대 타입을 구할 수 있다.")
    @CsvSource({"WIN,LOSE", "LOSE,WIN", "DRAW,DRAW"})
    void canReverse(WinningType originType, WinningType expectedType) {
        WinningType actualType = originType.reverse();
        assertThat(actualType).isEqualTo(expectedType);
    }

    @ParameterizedTest
    @DisplayName("플레이어의 카드점수와 딜러의 카드점수를 통해 승패타입을 구할 수 있다.")
    @MethodSource()
    void canParse(int playerPoint, int dealerPoint, WinningType expectedType) {
        WinningType actualType = WinningType.parse(playerPoint, dealerPoint);
        assertThat(actualType).isEqualTo(expectedType);
    }

    static Stream<Arguments> canParse() {
        return Stream.of(
                Arguments.of(WIN_POINT, LOSE_POINT, WinningType.WIN),
                Arguments.of(LOSE_POINT, WIN_POINT, WinningType.LOSE),
                Arguments.of(WIN_POINT, WIN_POINT, WinningType.DRAW),
                Arguments.of(WIN_POINT, BUST_POINT, WinningType.WIN),
                Arguments.of(BUST_POINT, WIN_POINT, WinningType.LOSE),
                Arguments.of(BUST_POINT, BUST_POINT, WinningType.DRAW)
        );
    }
}