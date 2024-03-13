package blackjack.model.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerResultTest {

    @DisplayName("결과값 '승리'를 추가한다.")
    @Test
    void addWin() {
        //given
        DealerResult dealerResult = new DealerResult();

        //when
        dealerResult.addWin();

        //then
        assertThat(dealerResult.getDealerResult().get(ResultState.WIN)).isEqualTo(1);
    }

    @DisplayName("결과값 '패배'를 추가한다.")
    @Test
    void addLose() {
        //given
        DealerResult dealerResult = new DealerResult();

        //when
        dealerResult.addLose();

        //then
        assertThat(dealerResult.getDealerResult().get(ResultState.LOSE)).isEqualTo(1);
    }

    @DisplayName("결과값 '무승부'를 추가한다.")
    @Test
    void addTie() {
        //given
        DealerResult dealerResult = new DealerResult();

        //when
        dealerResult.addTie();

        //then
        assertThat(dealerResult.getDealerResult().get(ResultState.TIE)).isEqualTo(1);
    }
}
