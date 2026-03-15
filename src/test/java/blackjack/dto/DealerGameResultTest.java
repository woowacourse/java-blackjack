package blackjack.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerGameResultTest {

    @Test
    @DisplayName("딜러의 수익률 dto 생성을 확인한다.")
    void makeDealerGameResult() {
        // given
        long profit = 5000;

        // when
        DealerGameResult dealerGameResult = DealerGameResult.from(profit);

        // then
        assertThat(dealerGameResult.profit()).isEqualTo(5000);
    }

}
