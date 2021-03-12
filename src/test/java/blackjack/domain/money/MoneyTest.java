package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.exception.BlackJackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("판돈 생성 테스트")
    void moneyTest() {
        int initialMoney = 1000;
        Money money = new Money(initialMoney);

        int rawMoney = money.getValue();

        assertThat(rawMoney).isEqualTo(initialMoney);
    }
}
