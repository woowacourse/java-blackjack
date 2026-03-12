package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BetTest {
    static Bet bet = new Bet();

    @BeforeAll
    static void setUp() {
        bet.addBetting("1", 10000);
        bet.addBetting("2", 20000);
        bet.addBetting("3", 40000);
    }

    @ParameterizedTest
    @MethodSource("results")
    @DisplayName("플레이어가 승이면 딜러가 손해를 봐야한다.")
    void 플레이어_승_딜러_손해(double expected, String name, WinningCondition condition) {
        // given

        // when
        double actual = bet.calculateEarningPrize(name, condition);

        // then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> results() {
        return Stream.of(
                Arguments.arguments(10000, "1", WinningCondition.WIN),
                Arguments.arguments(0, "2", WinningCondition.DRAW),
                Arguments.arguments(60000, "3", WinningCondition.BLACK_JACK),
                Arguments.arguments(15000, "1", WinningCondition.BLACK_JACK)
        );
    }
}
