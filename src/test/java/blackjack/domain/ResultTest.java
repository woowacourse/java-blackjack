package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @DisplayName("인자가 양수일때 WIN인지 테스트")
    @Test
    void valueOfTest() {
        assertThat(Result.valueOf(1)).isEqualTo(Result.LOSE);
    }

    @DisplayName("인자가 음수일때 LOSE인지 테스트")
    @Test
    void valueOfTest2() {
        assertThat(Result.valueOf(-1)).isEqualTo(Result.WIN);
    }

    @DisplayName("인자가 0일때 DRAW인지 테스트")
    @Test
    void valueOfTest3() {
        assertThat(Result.valueOf(0)).isEqualTo(Result.DRAW);
    }
}
