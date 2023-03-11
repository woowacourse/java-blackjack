package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("배팅 금액이 양수 인지 확인한다.")
    void validateMoneyRange() {
        // expect
        assertThatThrownBy(() -> new Money(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 0원 이상이여야 합니다.");
    }

    @Test
    @DisplayName("배팅 금액의 1.5배를 반환한다.")
    void getBlackjackPrize() {
        // given
        Money money = new Money(10000);

        // when
        Money prize = money.getBlackjackPrize();

        // then
        assertThat(prize).isEqualTo(new Money(15000));
    }

    @Test
    @DisplayName("배팅 금액을 반환한다.")
    void getBettingPrize() {
        // given
        Money money = new Money(10000);

        // when
        Money prize = money.getBettingPrize();

        // then
        assertThat(prize).isEqualTo(money);
    }

    @Test
    @DisplayName("배팅 금액만큼 잃은 금액을 반환한다.")
    void loseBettingPrize() {
        // given
        Money money = new Money(10000);

        // when
        Money prize = money.loseBettingPrize();

        // then
        assertThat(prize).isEqualTo(new Money(-10000));
    }
}
