package blackjack.domain.participants;

import static blackjack.domain.ExceptionMessage.INVALID_BETTING_AMOUNT_FORMAT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BettingMoneyTest {

    @DisplayName("베팅 금액이 1000 이하이면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "0", "999"})
    void should_ThrowException_When_AmountIsUnder0(final String amount) {
        assertThatThrownBy(() -> new BettingMoney(new BigDecimal(amount)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format(INVALID_BETTING_AMOUNT_FORMAT, 1000));
    }
}
