package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("1승 0패인 결과를 생성한다.")
    @Test
    void testCreateWinResult() {
        // when
        Result result = Result.createWinResult();

        // then
        assertAll(
                () -> assertThat(result.getWinCount()).isEqualTo(1),
                () -> assertThat(result.getLoseCount()).isEqualTo(0)
        );
    }

    @DisplayName("0승 1패인 결과를 생성한다.")
    @Test
    void testCreateLossResult() {
        // when
        Result result = Result.createLossResult();

        // then
        assertAll(
                () -> assertThat(result.getWinCount()).isEqualTo(0),
                () -> assertThat(result.getLoseCount()).isEqualTo(1)
        );
    }

    @DisplayName("주어진 결과와 반대로 상대의 결과를 업데이트한다.")
    @Test
    void testUpdateOpponent() {
        // given
        Result result = new Result();

        // when
        result.updateOpponent(Result.createWinResult());
        result.updateOpponent(Result.createWinResult());
        result.updateOpponent(Result.createLossResult());

        // then
        assertAll(
                () -> assertThat(result.getWinCount()).isEqualTo(1),
                () -> assertThat(result.getLoseCount()).isEqualTo(2)
        );
    }
}
