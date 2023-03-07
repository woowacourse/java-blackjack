package blackjack.domain.result;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatusTest {

    @Test
    @DisplayName("WinningStatus가 정의한 반대값을 반환하는지 테스트")
    void oppositeTest() {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(WinningStatus.WIN.opposite()).isEqualTo(WinningStatus.LOSE);
            softly.assertThat(WinningStatus.LOSE.opposite()).isEqualTo(WinningStatus.WIN);
            softly.assertThat(WinningStatus.TIE.opposite()).isEqualTo(WinningStatus.TIE);
        });
    }
}
