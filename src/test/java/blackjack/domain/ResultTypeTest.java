package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTypeTest {

    @Test
    @DisplayName("값 차이에 따른 게임 결과 테스트")
    void getResultType() {
        assertThat(ResultType.getResultType(1)).isEqualTo(ResultType.WIN);
        assertThat(ResultType.getResultType(0)).isEqualTo(ResultType.TIE);
        assertThat(ResultType.getResultType(-1)).isEqualTo(ResultType.LOSE);
    }
}
