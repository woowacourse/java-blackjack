package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingsTest {

    @DisplayName("베팅 정보를 등록할 수 있다.")
    @Test
    void placeBet() {
        // given
        Bettings bettings = new Bettings();
        Player player = new Player("pobi");
        Money bettingMoney = new Money(1000);

        // when
        bettings.placeBet(player, bettingMoney);

        // then
        assertThat(bettings.getBettingMoney(player)).isEqualTo(bettingMoney);
    }

    @DisplayName("베팅 정보가 없는 경우 예외를 발생한다.")
    @Test
    void getBettingMoney() {
        // given
        Bettings bettings = new Bettings();
        Player player = new Player("pobi");

        // when & then
        assertThatCode(() -> bettings.getBettingMoney(player))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("베팅 금액이 범위를 벗어나면 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {999, 10001})
    void validateBettingMoneyRange(int money) {
        // given
        Bettings bettings = new Bettings();
        Player player = new Player("pobi");
        Money bettingMoney = new Money(money);

        // when & then
        assertThatCode(() -> bettings.placeBet(player, bettingMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액은 1000원 단위여야 한다.")
    @Test
    void validateBettingMoneyUnit() {
        // given
        Bettings bettings = new Bettings();
        Player player = new Player("pobi");
        Money bettingMoney = new Money(1500);

        // when & then
        assertThatCode(() -> bettings.placeBet(player, bettingMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
