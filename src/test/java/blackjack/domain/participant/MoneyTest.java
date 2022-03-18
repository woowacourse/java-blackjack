package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {


    @DisplayName("머니가 정상적으로 생성되는지 확인")
    @Test
    void create() {
        String amount = "20000";
        Money money = new Money(amount);

        assertThat(money).isNotNull();
    }

    @DisplayName("머니의 값이 정상적으로 일치하는지 확인")
    @Test
    void equalsMoney() {
        String amount = "20000";
        Money money = new Money(amount);

        assertThat(money.getAmount()).isEqualTo(20000);
    }
}
