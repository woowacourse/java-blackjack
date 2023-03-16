package blackjack.domain.game;

import blackjack.domain.betting.Betting;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingTest {

    @Test
    @DisplayName("베팅값을 제대로 가져오는지 확인한다.")
    void getValueTest() {
        final int value = 10000;
        final Betting betting = Betting.from(value);

        Assertions.assertThat(betting.getValue()).isEqualTo(value);
    }
}
