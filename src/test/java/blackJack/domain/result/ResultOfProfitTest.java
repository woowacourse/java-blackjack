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
    BettingOutcome bettingOutcome;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        dealer.hit(Card.of(Symbol.CLOVER, Denomination.JACK));
        dealer.hit(Card.of(Symbol.CLOVER, Denomination.NINE));

        player1 = new Player("rookie");
        player1.hit(Card.of(Symbol.CLOVER, Denomination.ACE));
        player1.hit(Card.of(Symbol.CLOVER, Denomination.QUEEN));

        player2 = new Player("parang");
        player2.hit(Card.of(Symbol.CLOVER, Denomination.SEVEN));
        player2.hit(Card.of(Symbol.CLOVER, Denomination.KING));

        bettingOutcome = new BettingOutcome(Map.of(
                player1, new BettingAmount(10000),
                player2, new BettingAmount(20000)
        ));
    }

    @Test
    void getDealerProfit() {
        assertThat(bettingOutcome.getDealerProfit(dealer)).isEqualTo(5000);
    }

    @Test
    void getPlayersProfit() {
        assertThat(bettingOutcome.getPlayersProfit(dealer)).contains(
                Map.entry(player1, 15000),
                Map.entry(player2, -20000)
        );
    }
}