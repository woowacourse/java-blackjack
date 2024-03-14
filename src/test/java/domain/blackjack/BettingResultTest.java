package domain.blackjack;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

class BettingResultTest {

    @DisplayName("참가자가 승리했을 시 수익을 계산한다.")
    @Test
    void getPayoutWhenWin() {
        LinkedHashMap<Player, BetAmount> bet = new LinkedHashMap<>();
        Player player = new Player(new Name("one"));
        player.receiveCard(new Card(Shape.DIA, Rank.JACK));

        bet.put(player, new BetAmount(10_000));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.DIA, Rank.SIX));
        BettingResult bettingResult = new BettingResult(bet);

        double payout = bettingResult.getPayout(player, dealer);

        Assertions.assertThat(payout).isEqualTo(10_000);
    }

    @DisplayName("참가자가 패배했을 시 수익을 계산한다.")
    @Test
    void getPayoutWhenLose() {
        LinkedHashMap<Player, BetAmount> bet = new LinkedHashMap<>();
        Player player = new Player(new Name("one"));
        player.receiveCard(new Card(Shape.DIA, Rank.SIX));

        bet.put(player, new BetAmount(10_000));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.DIA, Rank.JACK));
        BettingResult bettingResult = new BettingResult(bet);

        double payout = bettingResult.getPayout(player, dealer);

        Assertions.assertThat(payout).isEqualTo(-10_000);
    }

    @DisplayName("참가자가 블랙잭이고 딜러는 블랙잭이 아닐 시 수익을 계산한다.")
    @Test
    void getPayoutWhenBlackJack() {
        LinkedHashMap<Player, BetAmount> bet = new LinkedHashMap<>();
        Player player = new Player(new Name("one"));
        player.receiveCard(new Card(Shape.DIA, Rank.JACK));
        player.receiveCard(new Card(Shape.DIA, Rank.ACE));

        bet.put(player, new BetAmount(10_000));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.DIA, Rank.SIX));
        BettingResult bettingResult = new BettingResult(bet);

        double payout = bettingResult.getPayout(player, dealer);

        Assertions.assertThat(payout).isEqualTo(15_000);
    }

    @DisplayName("참가자와 딜러 모두 블랙잭일 시 수익을 계산한다.")
    @Test
    void getPayoutWhenDraw() {
        LinkedHashMap<Player, BetAmount> bet = new LinkedHashMap<>();
        Player player = new Player(new Name("one"));
        player.receiveCard(new Card(Shape.DIA, Rank.JACK));
        player.receiveCard(new Card(Shape.DIA, Rank.ACE));

        bet.put(player, new BetAmount(10_000));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.CLOVER, Rank.ACE));
        dealer.receiveCard(new Card(Shape.CLOVER, Rank.QUEEN));
        BettingResult bettingResult = new BettingResult(bet);

        double payout = bettingResult.getPayout(player, dealer);

        Assertions.assertThat(payout).isZero();
    }

    @DisplayName("딜러의 수익을 계산한다.")
    @Test
    void getDealerPayout() {
        LinkedHashMap<Player, BetAmount> bet = new LinkedHashMap<>();
        Player one = new Player(new Name("one"));
        one.receiveCard(new Card(Shape.CLOVER, Rank.TEN));
        one.receiveCard(new Card(Shape.DIA, Rank.JACK));

        Player two = new Player(new Name("two"));
        two.receiveCard(new Card(Shape.DIA, Rank.KING));

        Player three = new Player(new Name("three"));
        three.receiveCard(new Card(Shape.SPADE, Rank.KING));
        three.receiveCard(new Card(Shape.SPADE, Rank.ACE));

        bet.put(one, new BetAmount(10_000));
        bet.put(two, new BetAmount(15_000));
        bet.put(three, new BetAmount(10_000));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.CLOVER, Rank.JACK));
        dealer.receiveCard(new Card(Shape.CLOVER, Rank.NINE));
        BettingResult bettingResult = new BettingResult(bet);

        double payout = bettingResult.getDealerPayout(dealer);

        Assertions.assertThat(payout).isEqualTo(-10_000);
    }
}
