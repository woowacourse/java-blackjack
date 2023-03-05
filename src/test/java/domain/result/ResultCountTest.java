package domain.result;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultCountTest {

    private ResultCount resultCount;

    @BeforeEach
    void init() {
        resultCount = new ResultCount();
    }

    @Test
    @DisplayName("승리 카운트를 추가한다.")
    void addWinCount() {
        resultCount.addWinCount();

        Assertions.assertThat(resultCount.findWinCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("무승부 카운트를 추가한다.")
    void addTieCount() {
        resultCount.addTieCount();

        Assertions.assertThat(resultCount.findTieCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("패배 카운트를 추가한다.")
    void addLoseCount() {
        resultCount.addLoseCount();

        Assertions.assertThat(resultCount.findLoseCount()).isEqualTo(1);
    }

}
