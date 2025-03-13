package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class BettingMoneyTest {

    @Test
    void 배팅금액을_가지는_배팅금을_생성할_수_있다() {
        BettingMoney bettingMoney = BettingMoney.of(10000);
        assertThat(bettingMoney).isEqualTo(BettingMoney.of(10000));
    }

    @ParameterizedTest
    @ValueSource(ints = {1111, 500_001, 999_999})
    void 배팅금이_1000원_단위가_아니면_예외가_발생한다(int amount) {
        assertThatThrownBy(() -> BettingMoney.of(amount))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 배팅금은_최소_1000원이다() {
        assertThatThrownBy(() -> BettingMoney.of(999))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 배팅금은_최대_100만원이다() {
        assertThatThrownBy(() -> BettingMoney.of(1_000_001))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 배팅금의_2배로_된_새로운_배팅금을_얻을_수_있다() {
        BettingMoney bettingMoney = BettingMoney.of(10000);
        BettingMoney twice = bettingMoney.twice();
        assertThat(twice.getAmount()).isEqualTo(20000);
    }

    @Test
    void 배팅금의_2배로_된_새로운_배팅금은_배팅금_상한을_넘을_수_있다() {
        BettingMoney bettingMoney = BettingMoney.of(1_000_000);

        assertAll(
            () -> assertThatCode(bettingMoney::twice).doesNotThrowAnyException(),
            () -> assertThat(bettingMoney.twice().getAmount()).isEqualTo(2_000_000)
        );
    }

    @DisplayName("배팅금의 1.5배로 된 새로운 배팅금을 얻을 수 있다.")
    @Test
    void canCreateNewBettingMoneyOfOnceHalf() {
        BettingMoney bettingMoney = BettingMoney.of(10000);
        BettingMoney twice = bettingMoney.onceHalf();
        assertThat(twice.getAmount()).isEqualTo(15000);
    }

    @DisplayName("배팅금의 1.5배로 된 새로운 배팅금은 배팅금 상한을 넘을 수 있다.")
    @Test
    void onceHalfBettingMoneyCanOvercomeLimit() {
        BettingMoney bettingMoney = BettingMoney.of(1_000_000);

        assertAll(
            () -> assertThatCode(bettingMoney::twice).doesNotThrowAnyException(),
            () -> assertThat(bettingMoney.twice().getAmount()).isEqualTo(2_000_000)
        );
    }
}
