package blackjack.domain.participant;


import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BetAmountTest {

    @DisplayName("1,000원 이상의 양수가 아닌 베팅금액을 생성하면 에러가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 999})
    public void createFail(int amount) {
        assertThatCode(() -> new BetAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("베팅금액은 1,000원 이상의 양수만 가능합니다. 입력값: %d", amount));
    }

    @DisplayName("베팅 금액이 1,000원 이상이면 에러가 발생하지 않는다")
    @Test
    public void createSuccess() {
        assertThatCode(() -> new BetAmount(1000))
                .doesNotThrowAnyException();
    }
}
