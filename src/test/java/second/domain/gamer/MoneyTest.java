package second.domain.gamer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import second.domain.card.Rank;
import second.domain.result.ResultType;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static second.domain.result.ResultType.*;

public class MoneyTest {
    private static Money money;

    @BeforeEach
    void beforeEach() {
        money = new Money(10);
    }

    @Test
    void initailize() {
        assertThat(money).isInstanceOf(Money.class);
    }

    @ParameterizedTest
    @DisplayName("수익률 계산")
    @MethodSource("generateResultType")
    void times(final ResultType resultType, final int moneyValue) {
        final Money profit = money.times(resultType.getProfitMultipleValue());
        assertThat(profit).isEqualTo(new Money(moneyValue));
    }

    private static Stream<Arguments> generateResultType() {
        return Stream.of(
                Arguments.of(ResultType.ONLY_PLAYER_BLACK_JACK, 15),
                Arguments.of(ResultType.DRAW, 0),
                Arguments.of(ResultType.WIN, 10),
                Arguments.of(ResultType.LOSE, -10)
        );
    }
}
