package domain.participants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {-10000, 0, 9999})
    @DisplayName("배팅 금액 범위 테스트")
    void bettingAmountValidateException(int amount) {
        assertThatThrownBy(() -> new BettingAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 최소 10000원 이상이여야합니다.");
    }
}
