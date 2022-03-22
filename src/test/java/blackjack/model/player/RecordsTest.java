package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class RecordsTest {

    private static final String GAMER_NAME = "name";

    @Test
    void dealerProfit() {
        Gamer gamer = new Gamer(GAMER_NAME, new Money(new BigDecimal("1000")),
            new Card(Rank.EIGHT, Suit.DIAMOND), new Card(Rank.ACE, Suit.HEART));

        Records records = new Records(List.of(
            new Record(gamer, Result.LOSS),
            new Record(gamer, Result.WIN),
            new Record(gamer, Result.WIN),
            new Record(gamer, Result.DRAW),
            new Record(gamer, Result.BLACKJACK)));

        assertThat(records.dealerProfit()).isEqualTo(money("-2500.0"));
    }

    private Money money(String s) {
        return new Money(new BigDecimal(s));
    }

}