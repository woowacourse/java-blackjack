package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("BettingMoney 는")
class BettingMoneyTest {

    @Test
    void 금액을_가지고_생성된다() {
        // given
        final int amount = 1000;

        // when
        BettingMoney bettingMoney = BettingMoney.of(amount);

        // then
        assertThat(bettingMoney.amount()).isEqualTo(amount);
    }

    @Test
    void 같은_금액인_경우_동등하다() {
        // given
        final int amount = 1000;
        BettingMoney bettingMoney1 = BettingMoney.of(amount);
        BettingMoney bettingMoney2 = BettingMoney.of(amount);

        // when & then
        assertThat(bettingMoney1).isEqualTo(bettingMoney2);
    }
}