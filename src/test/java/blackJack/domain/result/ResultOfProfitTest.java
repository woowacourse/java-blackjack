package blackJack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResultOfProfitTest {

    Dealer dealer;
    Player player1;
    Player player2;
    ResultOfProfit resultOfProfit;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        dealer.hit(Card.from(Symbol.CLOVER, Denomination.JACK));
        dealer.hit(Card.from(Symbol.CLOVER, Denomination.NINE));

        player1 = new Player("rookie");
        player1.hit(Card.from(Symbol.CLOVER, Denomination.ACE));
        player1.hit(Card.from(Symbol.CLOVER, Denomination.QUEEN));

        player2 = new Player("parang");
        player2.hit(Card.from(Symbol.CLOVER, Denomination.SEVEN));
        player2.hit(Card.from(Symbol.CLOVER, Denomination.KING));

        resultOfProfit = new ResultOfProfit(Map.of(player1, "10000", player2, "20000"));
    }

    @Test
    void getDealerProfit() {
        assertThat(resultOfProfit.getDealerProfit(dealer)).isEqualTo(10000);
    }

    @Test
    void getPlayersProfit() {
        assertThat(resultOfProfit.getPlayersProfit(dealer)).contains(
                Map.entry(player1, 10000),
                Map.entry(player2, -20000)
        );
    }
}