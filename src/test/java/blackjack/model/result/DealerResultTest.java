package blackjack.model.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DealerResultTest {

    @DisplayName("결과값 '승리'를 추가한다.")
    @Test
    void addWin() {
        //given
        DealerResult dealerResult = new DealerResult();

        //when
        dealerResult.addWin();

        //then
        assertThat(dealerResult.getDealerResult()).containsExactly(Result.WIN);
    }

    @DisplayName("결과값 '패배'를 추가한다.")
    @Test
    void addLose() {
        //given
        DealerResult dealerResult = new DealerResult();

        //when
        dealerResult.addLose();

        //then
        assertThat(dealerResult.getDealerResult()).containsExactly(Result.LOSE);
    }

    @DisplayName("결과값 '무승부'를 추가한다.")
    @Test
    void addTie() {
        //given
        DealerResult dealerResult = new DealerResult();

        //when
        dealerResult.addTie();

        //then
        assertThat(dealerResult.getDealerResult()).containsExactly(Result.TIE);
    }

    @DisplayName("결과값에 '승리' 개수를 반환한다.")
    @Test
    void countWins() {
        //given
        DealerResult dealerResult = new DealerResult();

        //when
        dealerResult.addWin();
        dealerResult.addWin();

        //then
        assertThat(dealerResult.countWins()).isEqualTo(2);
    }

    @DisplayName("결과값에 '패배' 개수를 반환한다.")
    @Test
    void countLoses() {
        //given
        DealerResult dealerResult = new DealerResult();

        //when
        dealerResult.addLose();
        dealerResult.addLose();

        //then
        assertThat(dealerResult.countLoses()).isEqualTo(2);
    }

    @DisplayName("결과값에 '무승부' 개수를 반환한다.")
    @Test
    void countTies() {
        //given
        DealerResult dealerResult = new DealerResult();

        //when
        dealerResult.addTie();
        dealerResult.addTie();

        //then
        assertThat(dealerResult.countTies()).isEqualTo(2);
    }
}
