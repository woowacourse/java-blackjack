package domain;

import domain.card.Card;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.result.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class RuleTest {

    @Test
    @DisplayName("플레이어가 이긴 경우 승을 반환한다.")
    void decideStatus_WIN() {
        Dealer dealer = new Dealer(List.of(new Card(5, Shape.CLUB), new Card(10, Shape.HEART)));
        Player player = new Player(
                new Name("capy"), new BetAmount(1000), List.of(new Card(7, Shape.CLUB), new Card(10, Shape.HEART)));

        Assertions.assertThat(Rule.decideStatus(player, dealer)).isEqualTo(Status.WIN);
    }

    @Test
    @DisplayName("딜러가 버스트인 경우 승을 반환한다.")
    void decideStatus_WIN_DealerBurst() {
        Dealer dealer = new Dealer(List.of(new Card(10, Shape.CLUB), new Card(10, Shape.HEART)));
        Player player = new Player(
                new Name("capy"), new BetAmount(1000), List.of(new Card(7, Shape.CLUB), new Card(10, Shape.HEART)));

        dealer.receive(new Card(2, Shape.CLUB));

        Assertions.assertThat(Rule.decideStatus(player, dealer)).isEqualTo(Status.WIN);
    }

    @Test
    @DisplayName("플레이어가 블랙잭인 경우 블랙잭을 반환한다.")
    void decideStatus_BLACKJACK() {
        Dealer dealer = new Dealer(List.of(new Card(5, Shape.CLUB), new Card(10, Shape.HEART)));
        Player player = new Player(
                new Name("capy"), new BetAmount(1000), List.of(new Card(1, Shape.CLUB), new Card(10, Shape.HEART)));

        Assertions.assertThat(Rule.decideStatus(player, dealer)).isEqualTo(Status.WIN_BLACKJACK);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 블랙잭인 경우 비김을 반환한다.")
    void decideStatus_BLACKJACK_TIE() {
        Dealer dealer = new Dealer(List.of(new Card(1, Shape.CLUB), new Card(10, Shape.HEART)));
        Player player = new Player(
                new Name("capy"), new BetAmount(1000), List.of(new Card(1, Shape.CLUB), new Card(10, Shape.HEART)));

        Assertions.assertThat(Rule.decideStatus(player, dealer)).isEqualTo(Status.TIE);
    }

    @Test
    @DisplayName("플레이어가 비긴 경우 비김을 반환한다.")
    void decideStatus_TIE() {
        Dealer dealer = new Dealer(List.of(new Card(5, Shape.CLUB), new Card(10, Shape.HEART)));
        Player player = new Player(
                new Name("capy"), new BetAmount(1000), List.of(new Card(5, Shape.CLUB), new Card(10, Shape.HEART)));

        Assertions.assertThat(Rule.decideStatus(player, dealer)).isEqualTo(Status.TIE);
    }

    @Test
    @DisplayName("플레이어가 진 경우 패을 반환한다.")
    void decideStatus_LOSE() {
        Dealer dealer = new Dealer(List.of(new Card(7, Shape.CLUB), new Card(10, Shape.HEART)));
        Player player = new Player(
                new Name("capy"), new BetAmount(1000), List.of(new Card(5, Shape.CLUB), new Card(10, Shape.HEART)));

        Assertions.assertThat(Rule.decideStatus(player, dealer)).isEqualTo(Status.LOSE);
    }
}
