package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTypeTest {

    static final int WIN_POINT = 21;
    static final int LOSE_POINT = 20;
    static final int BUST_POINT = 22;

    @ParameterizedTest
    @DisplayName("배율에 따른 수익을 계산할 수 있다.")
    @MethodSource()
    void calculateProfit(GameResultType gameResultType, int amount, int expectedProfit) {
        int actualProfit = gameResultType.calculateProfit(amount);
        assertThat(actualProfit).isEqualTo(expectedProfit);
    }

    static Stream<Arguments> calculateProfit() {
        return Stream.of(
                Arguments.of(GameResultType.WIN_WITH_INITIAL_HAND_BLACKJACK, 1000, 1500),
                Arguments.of(GameResultType.WIN, 1000, 1000),
                Arguments.of(GameResultType.LOSE, 1000, -1000),
                Arguments.of(GameResultType.DRAW, 1000, 0)
        );
    }

    @ParameterizedTest
    @DisplayName("플레이어의 카드점수와 딜러의 카드점수를 통해 승패타입을 구할 수 있다.")
    @MethodSource()
    void canParse(int playerPoint, int dealerPoint, GameResultType expectedType) {
        GameResultType actualType = GameResultType.parse(playerPoint, dealerPoint);
        assertThat(actualType).isEqualTo(expectedType);
    }

    static Stream<Arguments> canParse() {
        return Stream.of(
                Arguments.of(WIN_POINT, LOSE_POINT, GameResultType.WIN),
                Arguments.of(LOSE_POINT, WIN_POINT, GameResultType.LOSE),
                Arguments.of(WIN_POINT, WIN_POINT, GameResultType.DRAW),
                Arguments.of(WIN_POINT, BUST_POINT, GameResultType.WIN),
                Arguments.of(BUST_POINT, WIN_POINT, GameResultType.LOSE),
                Arguments.of(BUST_POINT, BUST_POINT, GameResultType.DRAW)
        );
    }
}