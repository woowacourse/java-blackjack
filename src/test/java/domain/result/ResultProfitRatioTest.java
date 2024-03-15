package domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ResultProfitRatioTest {
    @DisplayName("비율에 맞는 값을 찾을 수 있다.")
    @Test
    void match(){
        assertAll(
                () -> assertThat(ResultProfitRatio.match(1.5)).isEqualTo(ResultProfitRatio.BLACKJACK),
                () -> assertThat(ResultProfitRatio.match(1)).isEqualTo(ResultProfitRatio.WIN),
                () -> assertThat(ResultProfitRatio.match(0)).isEqualTo(ResultProfitRatio.PUSH),
                () -> assertThat(ResultProfitRatio.match(-1)).isEqualTo(ResultProfitRatio.LOSE)

        );
    }
}
