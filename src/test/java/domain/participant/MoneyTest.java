package domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MoneyTest {

    @Test
    void 배팅금이_양수가_아니면_오류가_발생한다() {
        Assertions.assertThatThrownBy(() -> new Money(BigDecimal.valueOf(-10)))
                .hasMessageContaining("[ERROR] [-10원]은 입력할 수 없습니다.");
    }

    @Test
    void 배팅금_객체는_자신의_배팅금을_알고_있다() {
        BigDecimal amount = BigDecimal.valueOf(10000);
        Money money = new Money(BigDecimal.valueOf(10000));

        assertThat(money.getBettingMoney()).isEqualByComparingTo(amount);
    }
}
