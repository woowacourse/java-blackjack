package domain.participant;

import domain.blackjack.BetAmount;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("사용자의 점수가 21이하면 카드를 받을 수 있다.")
    @Test
    void canHit() {
        Player player = new Player(new Name("user"), new BetAmount(1000));
        player.receiveCard(new Card(Shape.HEART, Rank.KING));
        player.receiveCard(new Card(Shape.DIA, Rank.FIVE));
        player.receiveCard(new Card(Shape.HEART, Rank.SIX));

        boolean canHit = player.canHit();

        Assertions.assertThat(canHit).isTrue();
    }

    @DisplayName("사용자의 점수가 22이상이면 카드를 받을 수 없다.")
    @Test
    void canNotHit() {
        Player player = new Player(new Name("user"), new BetAmount(1000));
        player.receiveCard(new Card(Shape.HEART, Rank.KING));
        player.receiveCard(new Card(Shape.DIA, Rank.KING));
        player.receiveCard(new Card(Shape.HEART, Rank.TWO));

        boolean canHit = player.canHit();

        Assertions.assertThat(canHit).isFalse();
    }

    @DisplayName("참가자가 승리했을 시 수익을 계산한다.")
    @Test
    void getPayoutWhenWin() {
        Player player = new Player(new Name("one"), new BetAmount(10_000));
        player.receiveCard(new Card(Shape.DIA, Rank.JACK));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.DIA, Rank.SIX));

        double payout = player.getPayout(dealer);

        Assertions.assertThat(payout).isEqualTo(10_000);
    }

    @DisplayName("참가자가 패배했을 시 수익을 계산한다.")
    @Test
    void getPayoutWhenLose() {
        Player player = new Player(new Name("one"), new BetAmount(10_000));
        player.receiveCard(new Card(Shape.DIA, Rank.SIX));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.DIA, Rank.JACK));

        double payout = player.getPayout(dealer);

        Assertions.assertThat(payout).isEqualTo(-10_000);
    }

    @DisplayName("참가자가 블랙잭이고 딜러는 블랙잭이 아닐 시 수익을 계산한다.")
    @Test
    void getPayoutWhenBlackJack() {
        Player player = new Player(new Name("one"), new BetAmount(10_000));
        player.receiveCard(new Card(Shape.DIA, Rank.JACK));
        player.receiveCard(new Card(Shape.DIA, Rank.ACE));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.DIA, Rank.SIX));

        double payout = player.getPayout(dealer);

        Assertions.assertThat(payout).isEqualTo(15_000);
    }

    @DisplayName("참가자와 딜러 모두 블랙잭일 시 수익을 계산한다.")
    @Test
    void getPayoutWhenDraw() {
        Player player = new Player(new Name("one"), new BetAmount(10_000));
        player.receiveCard(new Card(Shape.DIA, Rank.JACK));
        player.receiveCard(new Card(Shape.DIA, Rank.ACE));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.CLOVER, Rank.ACE));
        dealer.receiveCard(new Card(Shape.CLOVER, Rank.QUEEN));

        double payout = player.getPayout(dealer);

        Assertions.assertThat(payout).isZero();
    }
}
