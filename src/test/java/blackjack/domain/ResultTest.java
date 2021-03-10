package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ResultTest {
    @ParameterizedTest
    @DisplayName("수익이 올바르게 나왔는지 확인")
    @CsvSource(value = {"BLACKJACK:15000", "WIN:10000", "DRAW:0", "LOSE:-10000"}, delimiter = ':')
    void calculateRate(final Result result, final double expected) {
        final double money = 10000;
        Assertions.assertThat(result.calculateRate(money)).isEqualTo(expected);
    }
}
