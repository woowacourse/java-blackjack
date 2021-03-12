package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class MoneyTest {

    @Test
    @DisplayName("Money 생성 예외 테스트")
    void testValidate() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Money(""))
                .withMessage("공백을 입력으로 받을 수 없습니다.");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Money("aaa"))
                .withMessage("숫자를 입력하세요.");
    }

    @Test
    @DisplayName("수익률에 따른 수익금 계산 테스트")
    void testProfit() {
        //given
        Money money = new Money("10000");

        //when
        Money profit = money.calculateProfit(1.5);

        //then
        assertThat(profit).isEqualTo(new Money(15000));
    }
}
