package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandResultTest {
    @DisplayName("WIN과 반대되는 결과를 반환한다.")
    @Test
    void resultWinGetOpposite() {
        //given
        HandResult handResult = HandResult.WIN;

        //when & then
        assertThat(handResult.getOpposite()).isEqualTo(HandResult.LOSE);
    }

    @DisplayName("DRAW와 반대되는 결과를 반환한다.")
    @Test
    void resultDrawGetOpposite() {
        //given
        HandResult handResult = HandResult.DRAW;

        //when & then
        assertThat(handResult.getOpposite()).isEqualTo(HandResult.DRAW);
    }

    @DisplayName("LOSE와 반대되는 결과를 반환한다.")
    @Test
    void resultLoseGetOpposite() {
        //given
        HandResult handResult = HandResult.LOSE;

        //when & then
        assertThat(handResult.getOpposite()).isEqualTo(HandResult.WIN);
    }
}
