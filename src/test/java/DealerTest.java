import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Rank;
import blackjack.domain.Shape;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void dealer_total_score_blackjack() {
        Dealer dealer = new Dealer();
        dealer.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        dealer.receiveOneCard(new Card(Rank.QUEEN, Shape.SPADE));
        assertThat(dealer.calculateTotalScore()).isEqualTo(21);
    }

    @Test
    void dealer_total_score_not_blackjack() {
        Dealer dealer = new Dealer();
        dealer.receiveOneCard(new Card(Rank.THREE, Shape.HEART));
        dealer.receiveOneCard(new Card(Rank.TEN, Shape.SPADE));
        assertThat(dealer.calculateTotalScore()).isEqualTo(13);
    }

    @Test
    void is_dealer_done_when_total_score_is_17() {
        Dealer dealer = new Dealer();
        dealer.receiveOneCard(new Card(Rank.TEN, Shape.HEART));
        dealer.receiveOneCard(new Card(Rank.SEVEN, Shape.SPADE));
        assertThat(dealer.isDealerNotDone()).isEqualTo(false);
    }

    @Test
    void is_dealer_done_when_total_score_is_not_17() {
        Dealer dealer = new Dealer();
        dealer.receiveOneCard(new Card(Rank.TWO, Shape.HEART));
        dealer.receiveOneCard(new Card(Rank.SEVEN, Shape.SPADE));
        assertThat(dealer.isDealerNotDone()).isEqualTo(true);
    }
}
