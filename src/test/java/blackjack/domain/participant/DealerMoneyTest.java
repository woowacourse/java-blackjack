package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class DealerMoneyTest {
    @Test
    @DisplayName("DealerMoney 객체가 잘 생성되는지 테스트")
    public void DealerMoneyConstructor() {
        assertThatCode(() -> new DealerMoney())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("DealerMoney에 player수익 추가")
    public void DealerMoneyTest() {
        final DealerMoney dealerMoney = new DealerMoney();
        dealerMoney.calculateByOpponentProfit(1000);
        assertThat(dealerMoney.getMoney()).isEqualTo(-1000);
    }
}
