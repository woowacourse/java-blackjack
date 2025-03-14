package blackjack.model.blackjack_player.player.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -3})
    void 배팅_금액은_음수로_초기화할_수_없다(final int bettingMoney) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Betting.bet(bettingMoney));
    }

    @Test
    void 금액을_받아_보유_금엑에_더한다() {
        Betting betting = Betting.bet(1000);

        betting.earn(500);

        assertThat(betting.getBalance()).isEqualTo(1500);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -3})
    void 보유_금액에_음수를_더할_수_없다(final int money) {
        Betting betting = Betting.bet(1000);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> betting.earn(money));
    }

    @Test
    void 베팅_금액을_전부_잃을_수_있다() {
        Betting betting = Betting.bet(1000);

        betting.lose();

        assertThat(betting.getBalance()).isEqualTo(0);
    }
}
