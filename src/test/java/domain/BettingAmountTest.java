package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class BettingAmountTest {
    @DisplayName("음수인 경우 검증")
    @Test
    void 음수인_경우() {
        assertThatThrownBy(() -> new BettingAmount(-10000));
    }
}
