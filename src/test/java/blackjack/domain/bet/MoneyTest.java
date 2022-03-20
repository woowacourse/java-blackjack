package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("돈 생성자 테스트")
    void create() {
        Money money = new Money(10000);
        assertThat(money).isNotNull();
    }
}
