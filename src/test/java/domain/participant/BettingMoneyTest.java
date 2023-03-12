package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {

    @DisplayName("베팅 금액이 0 이상이면서 1000 단위면 생성에 성공한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 15000})
    void createTest(int money) {
        BettingMoney bettingMoney = new BettingMoney();
        assertThat(bettingMoney.getValue()).isEqualTo(money);
    }
}
