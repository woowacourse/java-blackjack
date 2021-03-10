package blackjack.domain.player;

import blackjack.exception.NotEnoughBetAmountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BetMoneyTest {

    @DisplayName("베팅 금액을 생성한다.")
    @Test
    void create() {
        BetMoney betMoney = new BetMoney(12345);

        assertThat(betMoney.getBetMoney()).isEqualTo(12345);
    }

    @DisplayName("올바르지 않은 배팅 금액을 입력할 때 예외를 발생한다.")
    @Test
    void validate() {
        assertThatThrownBy(() ->
            new BetMoney(0)
        ).isInstanceOf(NotEnoughBetAmountException.class);
    }
}