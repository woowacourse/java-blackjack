import domain.Amount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AmountTest {

    @Test
    @DisplayName("배팅 금액은 1000원 이상부터 가능하다")
    public void bet_min() {
        int amount = 0;
        assertThatThrownBy(() -> new Amount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 1000원 이상부터 가능합니다.");

    }

    @Test
    @DisplayName("배팅 금액은 1000원 단위로 가능하다")
    public void bet_unit() {
        int amount = 9999;
        assertThatThrownBy(() -> new Amount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 1000원 단위로 가능합니다.");
    }
}
