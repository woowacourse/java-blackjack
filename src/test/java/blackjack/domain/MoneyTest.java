package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @DisplayName("액수 같으면 같은 금액으로 본다.")
    @Test
    void create() {
        Money money = new Money(500);
        assertThat(money).isEqualTo(new Money(500));
    }
}
