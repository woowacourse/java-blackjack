package domain.constant;

import domain.Bet;
import domain.Card;
import domain.participant.Dealer;
import domain.Hand;
import domain.participant.Player;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static domain.constant.Rank.*;
import static domain.constant.Result.*;
import static domain.constant.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @Test
    void 플레이어가_버스트면_결과는_패다() {
        Dealer dealer = new Dealer();

        Player player = new Player("pobi", new Hand(), "10000");
        player.receiveCard(new Card(TEN, SPADE));
        player.receiveCard(new Card(TEN, HEART));
        player.receiveCard(new Card(FIVE, HEART));

        Result result = of(dealer, player);

        assertThat(result).isEqualTo(LOSE);
    }

    @Test
    void 딜러만_버스트면_결과는_승이다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(TEN, SPADE));
        dealer.receiveCard(new Card(TEN, HEART));
        dealer.receiveCard(new Card(FIVE, HEART));

        Player player = new Player("pobi", new Hand(), "10000");

        Result result = of(dealer, player);

        assertThat(result).isEqualTo(WIN);
    }

    @Test
    void 딜러와_플레이어의_점수를_비교하여_승패를_결정한다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(KING, HEART));
        dealer.receiveCard(new Card(NINE, SPADE));

        Player player1 = new Player("pobi", new Hand(), "10000");
        player1.receiveCard(new Card(EIGHT, HEART));
        player1.receiveCard(new Card(NINE, HEART));

        Player player2 = new Player("cary", new Hand(), "10000");
        player2.receiveCard(new Card(ACE, SPADE));
        player2.receiveCard(new Card(EIGHT, DIAMOND));

        Player player3 = new Player("jason", new Hand(), "10000");
        player3.receiveCard(new Card(JACK, HEART));
        player3.receiveCard(new Card(QUEEN, SPADE));

        Player player4 = new Player("jason", new Hand(), "10000");
        player4.receiveCard(new Card(ACE, HEART));
        player4.receiveCard(new Card(QUEEN, HEART));

        List<Result> results = Stream.of(player1, player2, player3, player4)
                .map(player -> of(dealer, player))
                .toList();

        assertThat(results).containsExactly(LOSE, DRAW, WIN,BLACKJACK);
    }
}