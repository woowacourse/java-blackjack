package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class BettingMoneyTest {

    @Test
    @DisplayName("Money 생성 예외 테스트")
    void testValidate() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BettingMoney(""))
                .withMessage("공백을 입력으로 받을 수 없습니다.");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BettingMoney("aaa"))
                .withMessage("숫자를 입력하세요.");
    }

    @Test
    @DisplayName("수익률에 따른 수익금 계산 테스트")
    void testProfit() {
        //given
        BettingMoney bettingMoney = new BettingMoney("10000");

        //when
        BettingMoney profit = bettingMoney.calculateProfit(1.5);

        //then
        assertThat(profit).isEqualTo(new BettingMoney(15000));
    }
}
