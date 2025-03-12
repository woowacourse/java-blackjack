package domain.participant;

import static domain.blackJack.MatchResult.BLACKJACK;
import static domain.blackJack.MatchResult.DRAW;
import static domain.blackJack.MatchResult.LOSE;
import static domain.blackJack.MatchResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import domain.blackJack.MatchResult;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MoneyTest {

    @ParameterizedTest
    @MethodSource("provideMatchResultForProfit")
    @DisplayName("최종 수익 테스트")
    void calculateProfit(MatchResult matchResult, int profit) {
        // given
        Money money = Money.from(10000);

        // when-then
        assertThat(money.calculateProfit(matchResult)).isEqualTo(profit);
    }

    private static Stream<Arguments> provideMatchResultForProfit(){
        return Stream.of(
                Arguments.of(WIN, 10000),
                Arguments.of(DRAW, 0),
                Arguments.of(BLACKJACK, 5000),
                Arguments.of(LOSE, -10000)
        );
    }
}
