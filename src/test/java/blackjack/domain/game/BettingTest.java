package blackjack.domain.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BettingTest {

    @Test
    @DisplayName("베팅값을 제대로 가져오는지 확인한다.")
    void getValueTest() {
        final int value = 10000;
        final Betting betting = Betting.from(value);

        Assertions.assertThat(betting.getValue()).isEqualTo(value);
    }
}
