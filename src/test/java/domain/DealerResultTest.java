package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerResultTest {

    DealerResult dealerResult;

    @BeforeEach
    void init(){
        dealerResult = new DealerResult();
    }

    @DisplayName("승 횟수를 1만큼 추가한다.")
    @Test
    void addWinCount(){
        //given
        int expectedCount = 1;

        //when
        dealerResult.addWinCount();

        //then
        assertThat(dealerResult.getWinCount()).isEqualTo(expectedCount);
    }

    @DisplayName("패 횟수를 1만큼 추가한다.")
    @Test
    void addLoseCount(){
        //given
        int expectedCount = 1;

        //when
        dealerResult.addLoseCount();

        //then
        assertThat(dealerResult.getLoseCount()).isEqualTo(expectedCount);
    }

    @DisplayName("무승부 횟수를 1만큼 추가한다.")
    @Test
    void addTieCount(){
        //given
        int expectedCount = 1;

        //when
        dealerResult.addTieCount();

        //then
        assertThat(dealerResult.getTieCount()).isEqualTo(expectedCount);
    }

}
