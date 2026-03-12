package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import strategy.AceDrawStrategy;

class BetTest {
    static Bet bet = new Bet();

//    @BeforeAll
//    static void setUp() {
//        AceDrawStrategy aceDrawStrategy = new AceDrawStrategy();
//        List<Card> cards = new ArrayList<>();
//        Hand hand = new Hand(aceDrawStrategy, cards);
//        bet.addBetting(new Player("1", hand), 10000);
//        bet.addBetting(new Player("2", hand), 20000);
//        bet.addBetting(new Player("3", hand), 40000);
//    }

    @BeforeAll
    static void setUp() {
        bet.addBetting("1", 10000);
        bet.addBetting("2", 20000);
        bet.addBetting("3", 40000);
    }

    @ParameterizedTest
    @MethodSource("results")
    @DisplayName("플레이어 상황에 따라 수익을 반환해야한다.")
    void 플레이어_승_딜러_손해(double expected, String player, WinningCondition condition) {
        // given

        // when
        double actual = bet.calculateEarningPrize(player, condition);

        // then
        assertEquals(expected, actual);
    }

//    private static Stream<Arguments> results() {
//        AceDrawStrategy aceDrawStrategy = new AceDrawStrategy();
//        List<Card> cards = new ArrayList<>();
//        Hand hand = new Hand(aceDrawStrategy, cards);
//        return Stream.of(
//                Arguments.arguments(10000, new Player("1", hand), WinningCondition.WIN),
//                Arguments.arguments(0, new Player("2", hand), WinningCondition.DRAW),
//                Arguments.arguments(-40000, new Player("3", hand), WinningCondition.LOSE),
//                Arguments.arguments(15000, new Player("1", hand), WinningCondition.BLACK_JACK)
//        );
//    }

    private static Stream<Arguments> results() {
        return Stream.of(
                Arguments.arguments(10000, "1", WinningCondition.WIN),
                Arguments.arguments(0, "2", WinningCondition.DRAW),
                Arguments.arguments(60000, "3", WinningCondition.BLACK_JACK),
                Arguments.arguments(15000, "1", WinningCondition.BLACK_JACK)
        );
    }
}
