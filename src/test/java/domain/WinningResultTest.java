package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningResultTest {

    @Test
    @DisplayName("승무패 산정 테스트")
    void calculate() {
        int dealerScore = 17;
        int playerScore = 16;
        Assertions.assertThat(WinningResult.calculate(dealerScore, playerScore))
            .isEqualTo(WinningResult.LOSE);
    }
}
