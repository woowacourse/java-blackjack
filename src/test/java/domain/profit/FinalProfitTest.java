package domain.profit;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalProfitTest {

    @Test
    @DisplayName("최종 수익에 값을 더한다.")
    void add() {
        // given
        FinalProfit finalProfit = new FinalProfit(10000.0);

        // when
        FinalProfit addFinalProfit = finalProfit.add(new FinalProfit(10000.0));

        // then
        Assertions.assertThat(addFinalProfit.getProfit()).isEqualTo(20000.0);
    }
}
