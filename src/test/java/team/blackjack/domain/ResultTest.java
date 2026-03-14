package team.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @Test
    void WIN인_경우_반대_결과값은_LOSE가_출력된다() {
        Result result = Result.WIN;

        assertThat(result.reverse()).isEqualTo(Result.LOSE);
    }

    @Test
    void LOSE인_경우_반대_결과값은_WIN이_출력된다() {
        Result result = Result.LOSE;

        assertThat(result.reverse()).isEqualTo(Result.WIN);
    }

    @Test
    void DRAW인_경우_반대_결과값은_DRAW가_출력된다() {
        Result result = Result.DRAW;

        assertThat(result.reverse()).isEqualTo(Result.DRAW);
    }
}
