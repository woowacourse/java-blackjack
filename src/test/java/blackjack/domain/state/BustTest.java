package blackjack.domain.state;

import blackjack.domain.TestSetUp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BustTest {
    @Test
    @DisplayName("블랙잭 상태 수익률 검증")
    void checkResult() {
        Bust bust = new Bust();
        assertThat(bust.profitRate(TestSetUp.createBustDealer(), 22)).isEqualTo(-1);
        assertThat(bust.profitRate(TestSetUp.createDealer(), 22)).isEqualTo(-1);
    }
}