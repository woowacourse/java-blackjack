package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @DisplayName("(승) 승부 결과에 따라 배팅금액이 달라진다.")
    @Test
    void should_getBetResult_When_Win() {
        Result result = Result.WIN;
        assertThat(result.getBetProfit(1_000)).isEqualTo(1_000);
    }

    @DisplayName("(패) 승부 결과에 따라 배팅금액이 달라진다.")
    @Test
    void should_getBetResult_When_Lose() {
        Result result = Result.LOSE;
        assertThat(result.getBetProfit(1_000)).isEqualTo(-1_000);
    }

    @DisplayName("(무승부) 승부 결과에 따라 배팅금액이 달라진다.")
    @Test
    void should_getBetResult_When_DRAW() {
        Result result = Result.DRAW;
        assertThat(result.getBetProfit(1_000)).isEqualTo(0);
    }

    @DisplayName("(블랙잭) 승부 결과에 따라 배팅금액이 달라진다.")
    @Test
    void should_getBetResult_When_BlackJack() {
        Result result = Result.BLACK_JACK;
        assertThat(result.getBetProfit(1_000)).isEqualTo(1_500);
    }
}
