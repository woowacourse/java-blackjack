package blackjack;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.PlayRecord;
import blackjack.domain.participant.Name;

class BettingTest {
    private static final Betting betting = new Betting(Name.of("pobi"), 10000);

    @Test
    @DisplayName("LOSS인 경우 배팅 금액을 전부 잃는다.")
    void bettingLoss() {
        //when
        long actual = betting.result(PlayRecord.LOSS);

        //then
        assertThat(actual).isEqualTo(-10000);
    }

    @Test
    @DisplayName("WIN인 경우 배팅 금액을 수익으로 받는다.")
    void bettingWin() {
        //when
        long actual = betting.result(PlayRecord.WIN);

        //then
        assertThat(actual).isEqualTo(10000);
    }

    @Test
    @DisplayName("PUSH인 경우 수익은 0이다.")
    void bettingPush() {
        //when
        long actual = betting.result(PlayRecord.PUSH);

        //then
        assertThat(actual).isEqualTo(0);
    }

    @Test
    @DisplayName("블랙잭인 경우 베팅 금액의 1.5배 반환")
    void blackjack() {
        //when
        long actual = betting.result(PlayRecord.BLACKJACK);

        //then
        assertThat(actual).isEqualTo(15000);
    }
}
