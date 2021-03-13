package blackjack.domain.state;

import blackjack.domain.TestSetUp;
import blackjack.domain.state.finished.Stay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StayTest {
    @Test
    @DisplayName("블랙잭 상태 수익률 검증")
    void checkResult() {
        Stay stay = new Stay();
        assertThat(stay.profitRate(TestSetUp.createBustDealer(), 20)).isEqualTo(1);
        assertThat(stay.profitRate(TestSetUp.createDealer(), 17)).isEqualTo(-1);
        assertThat(stay.profitRate(TestSetUp.createDealer(), 20)).isEqualTo(0);
    }
}