package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class GamblingMoneyTest {

    @Test
    void 배팅금액을_가지는_배팅금을_생성할_수_있다() {
        GamblingMoney gamblingMoney = GamblingMoney.bet(10000);
        assertThat(gamblingMoney).isEqualTo(GamblingMoney.bet(10000));
    }

    @ParameterizedTest
    @ValueSource(ints = {1111, 500_001, 999_999})
    void 배팅금이_1000원_단위가_아니면_예외가_발생한다(int amount) {
        assertThatThrownBy(() -> GamblingMoney.bet(amount))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 배팅금은_최소_1000원이다() {
        assertThatThrownBy(() -> GamblingMoney.bet(999))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 배팅금은_최대_100만원이다() {
        assertThatThrownBy(() -> GamblingMoney.bet(1_000_001))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 금액의_부호를_반전할_수_있다() {
        GamblingMoney money = GamblingMoney.bet(10000);
        GamblingMoney negative = money.negative();
        assertThat(negative.getAmount()).isEqualTo(-10000);
    }

    @Test
    void GamblingMoney들의_금액을_모두_합할_수_있다() {
        GamblingMoney money1 = GamblingMoney.bet(10000);
        GamblingMoney money2 = GamblingMoney.bet(5000);

        GamblingMoney sum = GamblingMoney.sum(List.of(money1, money2));
        assertThat(sum.getAmount()).isEqualTo(15000);
    }

    @DisplayName("금액의 1.5배로 된 금액을 얻을 수 있다.")
    @Test
    void canCreateNewBettingMoneyBetOnceHalf() {
        GamblingMoney gamblingMoney = GamblingMoney.bet(10000);
        GamblingMoney twice = gamblingMoney.onceHalf();
        assertThat(twice.getAmount()).isEqualTo(15000);
    }

    @DisplayName("배팅금의 1.5배로 된 새로운 배팅금은 배팅금 상한을 넘을 수 있다.")
    @Test
    void onceHalfBettingMoneyCanOvercomeLimit() {
        GamblingMoney money = GamblingMoney.bet(1_000_000);

        assertAll(
            () -> assertThatCode(money::onceHalf).doesNotThrowAnyException(),
            () -> assertThat(money.onceHalf().getAmount()).isEqualTo(1_500_000)
        );
    }

    @ParameterizedTest
    @CsvSource({"WIN,10000", "DRAW,0", "LOSE,-10000"})
    void 승무패_여부에_따른_수익금을_반환한다(Winning winning, int expectedFinalAmount) {
        GamblingMoney money = GamblingMoney.bet(10000);

        GamblingMoney finalMoney = money.calculateProfit(winning);
        assertThat(finalMoney.getAmount()).isEqualTo(expectedFinalAmount);
    }
}
