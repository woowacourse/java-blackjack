package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    @DisplayName("돈 객체 생성 테스트")
    void moneyTest() {
        assertDoesNotThrow(() -> new Money(10000));
    }

    @Test
    @DisplayName("돈 단위 테스트")
    void validateDivisibleByTenThousandTest() {
        assertThatThrownBy(() -> new Money(10001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 만원 단위입니다.");
    }


}
