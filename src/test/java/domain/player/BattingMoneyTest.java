package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("BattingMoney 는")
class BattingMoneyTest {

    @Test
    void 금액을_가지고_생성된다() {
        // given
        final int amount = 1000;

        // when
        BattingMoney battingMoney = BattingMoney.of(amount);

        // then
        assertThat(battingMoney.amount()).isEqualTo(amount);
    }

    @Test
    void 같은_금액인_경우_동등하다() {
        // given
        final int amount = 1000;
        BattingMoney battingMoney1 = BattingMoney.of(amount);
        BattingMoney battingMoney2 = BattingMoney.of(amount);

        // when & then
        assertThat(battingMoney1).isEqualTo(battingMoney2);
    }
}