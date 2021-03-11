package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

    @DisplayName("배팅 금액 생성 ")
    @Test
    void create() {
        BettingMoney bettingMoney = new BettingMoney(1000);

        assertThat(bettingMoney).isEqualTo(new BettingMoney(1000));
    }

    @DisplayName("배팅 금액 유효성 검사")
    @Test
    void validateBettingMoney() {
        assertThatThrownBy(() -> {
            new BettingMoney(0);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("최소 0원보다 큰 배팅 금액을 입력해주세요. ");
    }

}