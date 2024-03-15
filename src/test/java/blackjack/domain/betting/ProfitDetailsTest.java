package blackjack.domain.betting;

import blackjack.domain.player.Name;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitDetailsTest {

    @Test
    void 딜러의_수익이_음수다() {
        Map<Name, Profit> profits = Map.of(
                new Name("PlayerA"), new Profit(1000),
                new Name("PlayerB"), new Profit(2000),
                new Name("PlayerC"), new Profit(3000)
        );
        ProfitDetails profitDetails = new ProfitDetails(profits);

        assertThat(profitDetails.getDealerProfit()).isEqualTo(new Profit(-6000));
    }

    @Test
    void 딜러의_수익이_양수다() {
        Map<Name, Profit> profits = Map.of(
                new Name("PlayerA"), new Profit(1000),
                new Name("PlayerB"), new Profit(-2000),
                new Name("PlayerC"), new Profit(-3000)
        );
        ProfitDetails profitDetails = new ProfitDetails(profits);

        assertThat(profitDetails.getDealerProfit()).isEqualTo(new Profit(4000));
    }

}
