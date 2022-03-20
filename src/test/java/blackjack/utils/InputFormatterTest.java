package blackjack.utils;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.BettingMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputFormatterTest {

    @Test
    @DisplayName("숫자를 변환할 수 있다")
    void can_parse_number() {
        int number = InputFormatter.parseInt("100");
        assertThat(number).isEqualTo(100);
    }

    @Test
    @DisplayName("betting money를 반환할 수 있다")
    void can_parse_betting_money() {
        BettingMoney bettingMoney = InputFormatter.parseBettingMoney("user a", "10000");
        assertThat(bettingMoney).isEqualTo(new BettingMoney("user a", 10_000));
    }
}
