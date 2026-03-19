package domain;

import domain.constant.Result;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BettingMoneyTest {

    @Test
    void 배팅금액은_0보다_커야_한다() {
        assertThatThrownBy(() -> new BettingMoney(0L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 배팅금액은_음수가_될_수_없다() {
        assertThatThrownBy(() -> new BettingMoney(-1L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 배팅금액이_양수이면_정상_생성된다() {
        new BettingMoney(1000L);
    }

    @Test
    void 배팅금액이_최소값이면_허용된다() {
        new BettingMoney(1L);
    }

    @Test
    void 승리하면_배팅금액만큼_수익을_얻는다() {
        BettingMoney money = new BettingMoney(1000L);

        double result = money.calculateProceeds(Result.WIN);

        assertThat(result).isEqualTo(1000L);
    }

    @Test
    void 패배하면_배팅금액만큼_손실이_발생한다() {
        BettingMoney money = new BettingMoney(1000L);

        double result = money.calculateProceeds(Result.LOSE);

        assertThat(result).isEqualTo(-1000L);
    }

    @Test
    void 무승부면_수익은_0이다() {
        BettingMoney money = new BettingMoney(1000L);

        double result = money.calculateProceeds(Result.DRAW);

        assertThat(result).isEqualTo(0);
    }

    @Test
    void 블랙잭이면_1_5배_수익을_얻는다() {
        BettingMoney money = new BettingMoney(1000L);

        double result = money.calculateProceeds(Result.BLACKJACK);

        assertThat(result).isEqualTo(1500L);
    }

    @Test
    void 버스트면_배팅금액만큼_손실이_발생한다() {
        BettingMoney money = new BettingMoney(1000L);

        double result = money.calculateProceeds(Result.BUST);

        assertThat(result).isEqualTo(-1000L);
    }
}