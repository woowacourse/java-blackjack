package domain.card;

import domain.participant.BetAmount;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.result.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DealerTest {

    @Test
    @DisplayName("처음 지급받는 카드의 수가 두 장이 아니라면 예외가 발생한다.")
    void player_OverSize_ExceptionThrown() {
        assertThatThrownBy(() -> new Dealer(List.of(new Card(1, Shape.CLUB), new Card(2, Shape.CLUB), new Card(3, Shape.CLUB))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("딜러의 카드 숫자의 합이 최소 점수 조건 이하면 뽑을 수 있다.")
    void canDraw_isBelowMinScore_True() {
        Dealer dealer = new Dealer(new ArrayList<>(List.of(new Card(6, Shape.CLUB), new Card(10, Shape.CLUB))));

        assertThat(dealer.canDraw()).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드 숫자의 합이 최소 점수 조건 초과면 뽑을 수 없다.")
    void canDraw_isOverMinScore_False() {
        Dealer dealer = new Dealer(new ArrayList<>(List.of(new Card(7, Shape.CLUB), new Card(10, Shape.CLUB))));

        assertThat(dealer.canDraw()).isFalse();
    }

    @Test
    @DisplayName("딜러가 카드를 뽑는다.")
    void draw_SizeUp() {
        Dealer dealer = new Dealer(new ArrayList<>(List.of(new Card(6, Shape.CLUB), new Card(10, Shape.CLUB))));

        dealer.receive(new Card(1, Shape.CLUB));

        assertThat(dealer.cards).hasSize(3);
    }

    @Test
    @DisplayName("플레이어가 이긴 경우 승을 반환한다.")
    void decideStatus_WIN() {
        Dealer dealer = new Dealer(List.of(new Card(5, Shape.CLUB), new Card(10, Shape.HEART)));
        Player player = new Player(
                new Name("capy"), new BetAmount(1000), List.of(new Card(7, Shape.CLUB), new Card(10, Shape.HEART)));

        Assertions.assertThat(dealer.decideStatus(player)).isEqualTo(Status.WIN);
    }

    @Test
    @DisplayName("딜러가 버스트인 경우 승을 반환한다.")
    void decideStatus_WIN_DealerBurst() {
        Dealer dealer = new Dealer(List.of(new Card(10, Shape.CLUB), new Card(10, Shape.HEART)));
        Player player = new Player(
                new Name("capy"), new BetAmount(1000), List.of(new Card(7, Shape.CLUB), new Card(10, Shape.HEART)));

        dealer.receive(new Card(2, Shape.CLUB));

        Assertions.assertThat(dealer.decideStatus(player)).isEqualTo(Status.WIN);
    }

    @Test
    @DisplayName("플레이어가 블랙잭인 경우 블랙잭을 반환한다.")
    void decideStatus_BLACKJACK() {
        Dealer dealer = new Dealer(List.of(new Card(5, Shape.CLUB), new Card(10, Shape.HEART)));
        Player player = new Player(
                new Name("capy"), new BetAmount(1000), List.of(new Card(1, Shape.CLUB), new Card(10, Shape.HEART)));

        Assertions.assertThat(dealer.decideStatus(player)).isEqualTo(Status.WIN_BLACKJACK);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 블랙잭인 경우 비김을 반환한다.")
    void decideStatus_BLACKJACK_TIE() {
        Dealer dealer = new Dealer(List.of(new Card(1, Shape.CLUB), new Card(10, Shape.HEART)));
        Player player = new Player(
                new Name("capy"), new BetAmount(1000), List.of(new Card(1, Shape.CLUB), new Card(10, Shape.HEART)));

        Assertions.assertThat(dealer.decideStatus(player)).isEqualTo(Status.TIE);
    }

    @Test
    @DisplayName("플레이어가 비긴 경우 비김을 반환한다.")
    void decideStatus_TIE() {
        Dealer dealer = new Dealer(List.of(new Card(5, Shape.CLUB), new Card(10, Shape.HEART)));
        Player player = new Player(
                new Name("capy"), new BetAmount(1000), List.of(new Card(5, Shape.CLUB), new Card(10, Shape.HEART)));

        Assertions.assertThat(dealer.decideStatus(player)).isEqualTo(Status.TIE);
    }

    @Test
    @DisplayName("플레이어가 진 경우 패을 반환한다.")
    void decideStatus_LOSE() {
        Dealer dealer = new Dealer(List.of(new Card(7, Shape.CLUB), new Card(10, Shape.HEART)));
        Player player = new Player(
                new Name("capy"), new BetAmount(1000), List.of(new Card(5, Shape.CLUB), new Card(10, Shape.HEART)));

        Assertions.assertThat(dealer.decideStatus(player)).isEqualTo(Status.LOSE);
    }
}
