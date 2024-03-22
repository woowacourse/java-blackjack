package domain.participant.dealer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.card.Deck;
import domain.result.Profit;
import domain.result.ProfitFixture;

class DealerResultTest {

    @DisplayName("딜러의 수익을 뺄 수 있다.")
    @CsvSource(value = {"1000, -1000", "-1000, 1000"})
    @ParameterizedTest
    void subtract(int playerProfit, int expected) {
        Dealer dealer = new Dealer(Deck.create());
        DealerResult result = new DealerResult(dealer);

        result.subtract(ProfitFixture.amountOf(playerProfit));
        Profit actual = result.getProfit();

        assertThat(actual).isEqualTo(ProfitFixture.amountOf(expected));
    }
}