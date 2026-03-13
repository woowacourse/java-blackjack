package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchResultTest {
    @Test
    @DisplayName("승리 시 배팅금액의 1배")
    void 승리_시_수익() {
        //given
        BettingMoney bettingMoney = new BettingMoney(1000);

        //when
        int winMoney = MatchResult.WIN.calculateIncome(bettingMoney);

        //then
        assertThat(winMoney).isEqualTo(1000);
    }

    @Test
    @DisplayName("패배 시 배팅금액의 -1배")
    void 패배_시_수익() {
        //given
        BettingMoney bettingMoney = new BettingMoney(1000);

        //when
        int loseMoney = MatchResult.LOSE.calculateIncome(bettingMoney);

        //then
        assertThat(loseMoney).isEqualTo(-1000);
    }

    @Test
    @DisplayName("무승부 시 0")
    void 무승부_시_금액() {
        //given
        BettingMoney bettingMoney = new BettingMoney(1000);

        //when
        int drawMoney = MatchResult.DRAW.calculateIncome(bettingMoney);

        //then
        assertThat(drawMoney).isEqualTo(0);
    }

    @Test
    @DisplayName("블랙잭 시 배팅금액의 1.5배")
    void 블랙잭_시_수익() {
        //given
        BettingMoney bettingMoney = new BettingMoney(1000);

        //when
        int blackJackMoney = MatchResult.BLACKJACK.calculateIncome(bettingMoney);

        //then
        assertThat(blackJackMoney).isEqualTo(1500);
    }
}
