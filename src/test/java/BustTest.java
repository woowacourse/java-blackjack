import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Rank;
import blackjack.domain.Shape;
import org.junit.jupiter.api.Test;

public class BustTest {

    @Test
    void player_busts_when_sum_exceeds_21() {
        Player player = new Player("Alice");
        player.receiveOneCard(new Card(Rank.TWO, Shape.HEART));
        player.receiveOneCard(new Card(Rank.QUEEN, Shape.SPADE));
        player.receiveOneCard(new Card(Rank.TEN, Shape.CLUB));

        assertThat(player.isBust()).isEqualTo(true);
    }

    @Test
    void dealer_busts_when_sum_exceeds_21() {
        Dealer dealer = new Dealer();
        dealer.receiveOneCard(new Card(Rank.TWO, Shape.HEART));
        dealer.receiveOneCard(new Card(Rank.QUEEN, Shape.SPADE));
        dealer.receiveOneCard(new Card(Rank.TEN, Shape.CLUB));

        assertThat(dealer.isBust()).isEqualTo(true);
    }

    @Test
    void player_not_busts_when_sum_21_or_less() {
        Player player = new Player("Alice");
        player.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        player.receiveOneCard(new Card(Rank.QUEEN, Shape.SPADE));
        player.receiveOneCard(new Card(Rank.TEN, Shape.CLUB));

        assertThat(player.isBust()).isEqualTo(false);
    }

    @Test
    void dealer_not_busts_when_sum_21_or_less() {
        Dealer dealer = new Dealer();
        dealer.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        dealer.receiveOneCard(new Card(Rank.QUEEN, Shape.SPADE));
        dealer.receiveOneCard(new Card(Rank.TEN, Shape.CLUB));

        assertThat(dealer.isBust()).isEqualTo(false);
    }


}
