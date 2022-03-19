package blackjack.model.bet;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.player.Dealer;
import blackjack.model.player.Entry;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitsTest {

    @DisplayName("참가자가 20000원을 잃고 10000원을 얻으면 딜러의 수익은 10000원이다")
    @Test
    void getDealerProfit_10000() {
        Map<Entry, Amount> entryProfits = new HashMap<>();
        entryProfits.put(new Entry("포키"), new Amount(-20000));
        entryProfits.put(new Entry("리버"), new Amount(10000));
        Dealer dealer = new Dealer();
        Profits profits = Profits.of(entryProfits, dealer);

        Amount actualAmount = profits.getValues().get(dealer);
        assertThat(actualAmount.getValue()).isEqualTo(10000);
    }
}
