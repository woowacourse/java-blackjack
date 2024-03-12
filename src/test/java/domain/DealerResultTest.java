package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerResultTest {

    DealerResult dealerResult;

    @BeforeEach
    void init() {
        dealerResult = new DealerResult();
    }

    @DisplayName("승 횟수를 1만큼 추가한다.")
    @Test
    void addWinCount() {
        //given
        Result result = Result.WIN;
        int expectedCount = 1;

        //when
        dealerResult = dealerResult.addResult(result);

        //then
        assertThat(dealerResult.getWinCount()).isEqualTo(expectedCount);
    }

    @DisplayName("패 횟수를 1만큼 추가한다.")
    @Test
    void addLoseCount() {
        //given
        Result result = Result.LOSE;
        int expectedCount = 1;

        //when
        dealerResult = dealerResult.addResult(result);

        //then
        assertThat(dealerResult.getLoseCount()).isEqualTo(expectedCount);
    }

    @DisplayName("무승부 횟수를 1만큼 추가한다.")
    @Test
    void addTieCount() {
        //given
        Result result = Result.TIE;
        int expectedCount = 1;

        //when
        dealerResult = dealerResult.addResult(result);

        //then
        assertThat(dealerResult.getTieCount()).isEqualTo(expectedCount);
    }

}
