import domain.participant.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class MoneyTest {

    @Test
    void 배팅금이_양수가_아니면_오류가_발생한다() {
        Assertions.assertThatThrownBy(() -> new Money(BigDecimal.valueOf(-10)))
                .hasMessageContaining("[ERROR] 배팅금은 0보다 커야 합니다!");
    }
}
