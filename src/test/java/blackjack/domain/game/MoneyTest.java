package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {

    @Test
    @DisplayName("금액을 입력받아 생성한다.")
    void create() {
        assertThat(new Money(10000)).isNotNull();
    }

    @Test
    @DisplayName("수익율을 계산한 금액을 반환한다")
    void multiply() {
        Money beforeMoney = new Money(10000);

        double yield = 1.5;
        Money afterMoney = beforeMoney.multiply(yield);

        assertThat(afterMoney).isEqualTo(new Money(15000));
    }
}


