package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingAmountTest {
    @DisplayName("BettingAmount를 생성한다.")
    @Test
    void Should_Create_When_NewBettingAmount() {
        BettingAmount bettingAmount = new BettingAmount(10000);

        assertThat(bettingAmount.getBettingAmount()).isEqualTo(10000);
    }

    @DisplayName("배팅 금액이 마이너스 일 때 예외 처리를 한다.")
    @Test
    void Should_ThrowException_When_BettingAmountLessThenZero() {
        assertThatThrownBy(() -> new BettingAmount(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 0원 이상이어야 합니다.");
    }


}
