package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.BettingAmountOutOfRangeException;
import exception.InvalidBettingUnitException;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    @Test
    void 베팅_금액은_1000원_단위여야_한다() {
        assertThatThrownBy(() -> new Money(1500))
                .isInstanceOf(InvalidBettingUnitException.class);
    }

    @Test
    void 베팅_금액은_최소_1000원_이상이어야_한다() {
        assertThatThrownBy(() -> new Money(999))
                .isInstanceOf(BettingAmountOutOfRangeException.class);

    }

    @Test
    void 베팅_금액은_최대_100000원_이하여야_한다() {
        assertThatThrownBy(() -> new Money(100001))
                .isInstanceOf(BettingAmountOutOfRangeException.class);
    }

    @Test
    void 정상적인_금액은_객체가_생성된다() {
        assertThatCode(() -> new Money(50000))
                .doesNotThrowAnyException();
    }
}
