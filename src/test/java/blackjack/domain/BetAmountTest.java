package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class BetAmountTest {

    @Test
    void 배팅_금액이_정상_생성된다() {
        BetAmount betAmount = BetAmount.of(1000);
        assertThat(betAmount).isNotNull();
    }

    @Test
    void 배팅_금액이_최소금액_미만이면_예외_발생한다() {
        int money = -1000;
        assertThatThrownBy(() -> BetAmount.of(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 최소 0원 이상이어야 합니다.");
    }

    @Test
    void 배팅_금액이_최대금액_미만이면_예외_발생한다() {
        int money = 200000;
        assertThatThrownBy(() -> BetAmount.of(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 최대 100000원 이하이어야 합니다.");
    }


}