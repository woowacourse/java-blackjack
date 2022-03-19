package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @Test
    @DisplayName("숫자를 입력하면 수익 객체가 생성된다.")
    void create() {
        int input = 10000;

        assertThat(new Profit(input)).isInstanceOf(Profit.class);
    }
}