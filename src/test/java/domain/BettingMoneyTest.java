package domain;

import domain.participant.BettingMoney;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BettingMoneyTest {

    @ParameterizedTest(name = "금액이 {0}원 일 때, 배팅 머니가 생성된다")
    @ValueSource(ints = {1, 2})
    void 배팅금액이_0원_초과이면_배팅머니를_만들_수_있다(int validMoney) {
        BettingMoney bettingMoney = BettingMoney.of(validMoney);
        assertThat(bettingMoney.getMoney()).isEqualTo(validMoney);
    }

    @ParameterizedTest(name = "금액이 {0}원 일 때, 배팅 머니는 생성할 수 없다")
    @ValueSource(ints = {0, -1})
    void 배팅금액이_0이하면_예외가_발생한다(int invalidMoney) {
        assertThatThrownBy(() -> BettingMoney.of(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
