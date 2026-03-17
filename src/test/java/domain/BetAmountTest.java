package domain;

import domain.participant.BetAmount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BetAmountTest {
    @Test
    void 베팅_시_0원_이하의_금액을_베팅하면_예외를_발생시킨다(){
        assertThatThrownBy(()->new BetAmount(BigDecimal.ZERO))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
