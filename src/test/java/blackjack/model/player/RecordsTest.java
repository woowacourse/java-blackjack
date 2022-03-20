package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.player.matcher.Money;
import blackjack.model.player.matcher.Record;
import blackjack.model.player.matcher.Result;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class RecordsTest {

    private static final String GAMER_NAME = "name";

    @Test
    void dealerProfit() {
        Records records = new Records(List.of(
            new Record(GAMER_NAME, Result.loss(money("1000"))),
            new Record(GAMER_NAME, Result.win(money("1000"))),
            new Record(GAMER_NAME, Result.win(money("1000"))),
            new Record(GAMER_NAME, Result.draw(money("3000"))),
            new Record(GAMER_NAME, Result.blackjack(money("1000")))));

        assertThat(records.dealerProfit()).isEqualTo(money("-2500.0"));
    }

    private Money money(String s) {
        return new Money(new BigDecimal(s));
    }

}