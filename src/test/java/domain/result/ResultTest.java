package domain.result;

import domain.card.Card;
import domain.participant.Dealer;
import domain.Hand;
import domain.participant.Player;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static domain.card.Rank.*;
import static domain.result.Result.*;
import static domain.card.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertThat(results).containsExactly(LOSE, DRAW, WIN, BLACKJACK);
    }


    @Test
    void 게임_결과에_따라_베팅_금액에_대한_수익금을_계산한다() {
        int BetAmount = 10_000;

        int blackjackProfit = BLACKJACK.calculateProfit(BetAmount);
        int winProfit = WIN.calculateProfit(BetAmount);
        int drawProfit = DRAW.calculateProfit(BetAmount);
        int loseProfit = LOSE.calculateProfit(BetAmount);

        assertAll(
                () -> assertEquals(15_000, blackjackProfit),
                () -> assertEquals(10_000, winProfit),
                () -> assertEquals(0, drawProfit),
                () -> assertEquals(-10_000, loseProfit)
        );
    }
}