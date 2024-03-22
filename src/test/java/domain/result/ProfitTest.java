package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.participant.attributes.Bet;

class ProfitTest {

    @DisplayName("수익을 뺄 수 있다.")
    @Test
    void subtract() {
        Profit profit1 = ProfitFixture.amountOf(1000);
        Profit profit2 = ProfitFixture.amountOf(300);
        assertThat(profit1.subtract(profit2)).isEqualTo(ProfitFixture.amountOf(700));
    }

    static Stream<Arguments> of() {
        return Stream.of(
                Arguments.of(new Bet(1000), ProfitRate.BLACKJACK, ProfitFixture.amountOf(1500)),
                Arguments.of(new Bet(1000), ProfitRate.WIN, ProfitFixture.amountOf(1000)),
                Arguments.of(new Bet(2000), ProfitRate.LOSE, ProfitFixture.amountOf(-2000)),
                Arguments.of(new Bet(3000), ProfitRate.PUSH, ProfitFixture.amountOf(0))
        );
    }

    @DisplayName("베팅 금액과 수익률로 수익을 계산할 수 있다.")
    @MethodSource
    @ParameterizedTest
    void of(Bet bet, ProfitRate profitRate, Profit expected) {
        assertThat(Profit.of(bet, profitRate)).isEqualTo(expected);
    }
}
