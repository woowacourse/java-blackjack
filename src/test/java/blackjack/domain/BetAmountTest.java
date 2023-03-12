package blackjack.domain;

import blackjack.domain.user.BetAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BetAmountTest {

    @DisplayName("배팅 금액을 갖는다.")
    @Test
    void generateBetAmount() {
        // given
        int amount = 10000;

        // when & then
        assertDoesNotThrow(() -> new BetAmount(amount));
    }

    @DisplayName("배팅 금액이 500원 이하 1,000,000원 이상일 경우 예외처리 한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 50, 1000001, 10000000})
    void validateBetAmountRange(int amount) {
        // when & then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BetAmount(amount));
    }
}
