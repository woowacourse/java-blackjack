package blackjack.domain;

import blackjack.domian.WinnerDecider;
import blackjack.domian.WinningResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class WinnerDeciderTest {

    @Test
    void 딜러의_점수와_같으면_무승부다() {
        //given
        int dealerScore = 19;
        WinnerDecider winnerDecider = new WinnerDecider(dealerScore);

        //when
        WinningResult result = winnerDecider.decide(19);

        //then
        Assertions.assertThat(result).isEqualTo(WinningResult.DRAW);
    }
}
