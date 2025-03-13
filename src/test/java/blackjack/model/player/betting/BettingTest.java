package blackjack.model.player.betting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BettingTest {

    @Test
    void 금액을_받아_보유_금엑에_더한다() {
        Betting betting = Betting.bet(1000);

        betting.earn(500);

        assertThat(betting.getBalance()).isEqualTo(1500);
    }

    @Test
    void 베팅_금액을_전부_잃을_수_있다() {
        Betting betting = Betting.bet(1000);

        betting.lose();

        assertThat(betting.getBalance()).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource(value = {"1000,500,500", "1000,1500,1500"})
    void 수익을_계산할_수_있다(int bettingMoney, int earnMoney, int expected) {
        Betting betting = Betting.bet(bettingMoney);

        betting.earn(earnMoney);

        assertThat(betting.getProfit()).isEqualTo(expected);
    }
}
