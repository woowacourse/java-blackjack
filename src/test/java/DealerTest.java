import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Rank;
import blackjack.domain.Shape;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void calculate_dealer_total_score() {
        Dealer dealer1 = new Dealer();
        dealer1.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        dealer1.receiveOneCard(new Card(Rank.QUEEN, Shape.SPADE));
        assertThat(dealer1.calculateTotalScore()).isEqualTo(21);

        Dealer dealer2 = new Dealer();
        dealer2.receiveOneCard(new Card(Rank.THREE, Shape.HEART));
        dealer2.receiveOneCard(new Card(Rank.TEN, Shape.SPADE));
        assertThat(dealer2.calculateTotalScore()).isEqualTo(13);
    }

    @Test
    void is_dealer_done_when_total_score_is_17_or_more() {
        Dealer dealer1 = new Dealer();
        dealer1.receiveOneCard(new Card(Rank.TEN, Shape.HEART));
        dealer1.receiveOneCard(new Card(Rank.SEVEN, Shape.SPADE));
        assertThat(dealer1.isDealerNotDone()).isEqualTo(false);

        Dealer dealer2 = new Dealer();
        dealer2.receiveOneCard(new Card(Rank.TWO, Shape.HEART));
        dealer2.receiveOneCard(new Card(Rank.SEVEN, Shape.SPADE));
        assertThat(dealer2.isDealerNotDone()).isEqualTo(true);
    }
}
