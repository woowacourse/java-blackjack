package domain;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.player.Dealer;
import domain.player.DealerStatus;
import domain.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("dealer는 점수가 16점 이하일때 뽑을 수 있다.")
    void dealerHittableTest() {
        //given
        Dealer dealer = new Dealer();
        //then
        assertThat(dealer.isHittable()).isTrue();
    }

    @Test
    @DisplayName("dealer는 점수가 16점 일때 뽑을 수 있다.")
    void dealerHittableTest2() {
        //given
        Dealer dealer = new Dealer();
        Card card1 = new Card(Suit.HEART, Denomination.SIX);
        Card card2 = new Card(Suit.HEART, Denomination.QUEEN);
        //when
        dealer.drawCard(card1);
        dealer.drawCard(card2);
        //then
        assertThat(dealer.isHittable()).isTrue();
    }

    @Test
    @DisplayName("dealer는 점수가 17점 이상이면 뽑을 수 없다.")
    void dealerHittableTest3() {
        //given
        Dealer dealer = new Dealer();
        Card card1 = new Card(Suit.HEART, Denomination.SEVEN);
        Card card2 = new Card(Suit.HEART, Denomination.QUEEN);
        //when
        dealer.drawCard(card1);
        dealer.drawCard(card2);
        //then
        assertThat(dealer.isHittable()).isFalse();
    }

    @Test
    @DisplayName("players와 dealer의 점수를 비교한다.")
    void dealerCompareWithPlayersTest() {
        //given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("ori", "jude"));
        //when
        dealer.drawCard(new Card(Suit.HEART, Denomination.ACE));
        players.getPlayers().get(0).drawCard(new Card(Suit.HEART, Denomination.NINE));
        players.getPlayers().get(1).drawCard(new Card(Suit.HEART, Denomination.JACK));
        players.getPlayers().get(1).drawCard(new Card(Suit.HEART, Denomination.JACK));
        List<DealerStatus> dealerStats = dealer.getDealerStats(players);
        //then
        assertThat(dealerStats).containsExactly(DealerStatus.WIN, DealerStatus.LOSE);
    }

    @Test
    @DisplayName("player가 blackjack이면 dealer가 진다.")
    void dealerCompareWithPlayersTest2() {
        //given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("jude"));
        //when
        dealer.drawCard(new Card(Suit.HEART, Denomination.ACE));
        dealer.drawCard(new Card(Suit.HEART, Denomination.JACK));
        players.getPlayers().get(0).drawCard(new Card(Suit.HEART, Denomination.ACE));
        players.getPlayers().get(0).drawCard(new Card(Suit.HEART, Denomination.JACK));
        List<DealerStatus> dealerStats = dealer.getDealerStats(players);
        //then
        assertThat(dealerStats).containsExactly(DealerStatus.LOSE);
    }

    @Test
    @DisplayName("player와 dealer가 둘다 버스트라면 비긴다.")
    void dealerCompareWithPlayersTest3() {
        //given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("jude"));
        //when
        dealer.drawCard(new Card(Suit.HEART, Denomination.JACK));
        dealer.drawCard(new Card(Suit.HEART, Denomination.JACK));
        dealer.drawCard(new Card(Suit.HEART, Denomination.JACK));
        players.getPlayers().get(0).drawCard(new Card(Suit.HEART, Denomination.JACK));
        players.getPlayers().get(0).drawCard(new Card(Suit.HEART, Denomination.JACK));
        players.getPlayers().get(0).drawCard(new Card(Suit.HEART, Denomination.JACK));
        List<DealerStatus> dealerStats = dealer.getDealerStats(players);
        //then
        assertThat(dealerStats).containsExactly(DealerStatus.DRAW);
    }
}
