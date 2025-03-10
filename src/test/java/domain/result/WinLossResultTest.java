package domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinLossResultTest {

    @Test
    @DisplayName("승무패 반환 테스트")
    void test1() {
        assertAll(() -> assertThat(WinLossResult.of(1)).isEqualTo(WinLossResult.WIN),
                () -> assertThat(WinLossResult.of(-1)).isEqualTo(WinLossResult.LOSS),
                () -> assertThat(WinLossResult.of(0)).isEqualTo(WinLossResult.DRAW));
    }
}
