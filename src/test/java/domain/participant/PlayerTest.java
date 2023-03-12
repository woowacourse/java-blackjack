package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import domain.betting.PlayerBettingResult;
import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void init() {
        this.player = new Player(new Name("seongha"), new Hand(Collections.emptyList()));
    }

    @Test
    @DisplayName("가지고 있는 카드의 합이 21 이하면 true를 반환한다.")
    void isCardValueBelow21() {
        player.takeCard(new Card(Suit.DIAMOND, Denomination.TEN));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.SIX));
        assertThat(player.canHit()).isTrue();
    }

    @Test
    @DisplayName("가지고 있는 카드의 합이 22 이상이면 false를 반환한다.")
    void isCardValueOver21() {
        player.takeCard(new Card(Suit.DIAMOND, Denomination.TEN));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.SIX));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.EIGHT));
        assertThat(player.canHit()).isFalse();
    }

    @Test
    @DisplayName("딜러가 블랙잭이면 배팅 결과는 LOSE이다.")
    void calculateBettingResult1() {
        // given
        Hand dealerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.ACE),
                new Card(Suit.HEART, Denomination.TEN)));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.TEN));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.SIX));

        // when, then
        assertThat(player.calculateBettingResult(dealerHand)).isEqualTo(PlayerBettingResult.LOSE);
    }

    @Test
    @DisplayName("플레이어가 버스트면 배팅 결과는 LOSE이다.")
    void calculateBettingResult2() {
        // given
        Hand dealerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.SIX),
                new Card(Suit.HEART, Denomination.TEN)));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.TEN));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.SIX));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.NINE));

        // when, then
        assertThat(player.calculateBettingResult(dealerHand)).isEqualTo(PlayerBettingResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러가 스테이일 때 플레이어의 카드 합이 딜러의 카드 합보다 적으면 배팅 결과는 LOSE이다.")
    void calculateBettingResult3() {
        // given
        Hand dealerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.SIX),
                new Card(Suit.HEART, Denomination.TEN)));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.THREE));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.FIVE));

        // when, then
        assertThat(player.calculateBettingResult(dealerHand)).isEqualTo(PlayerBettingResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 스테이일 때 딜러가 버스트면 배팅 결과는 WIN이다.")
    void calculateBettingResult4() {
        // given
        Hand dealerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.SIX),
                new Card(Suit.HEART, Denomination.TEN),
                new Card(Suit.SPADE, Denomination.TEN)));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.THREE));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.FIVE));

        // when, then
        assertThat(player.calculateBettingResult(dealerHand)).isEqualTo(PlayerBettingResult.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러가 스테이일 때 플레이어의 카드 합이 딜러의 카드 합보다 적으면 배팅 결과는 LOSE이다.")
    void calculateBettingResult5() {
        // given
        Hand dealerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.THREE),
                new Card(Suit.HEART, Denomination.FIVE)));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.JACK));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.SEVEN));

        // when, then
        assertThat(player.calculateBettingResult(dealerHand)).isEqualTo(PlayerBettingResult.WIN);
    }

    @Test
    @DisplayName("딜러가 블랙잭이 아닐 때 플레이어가 블랙잭이면 배팅 결과는 BLACKJACK이다.")
    void calculateBettingResult6() {
        // given
        Hand dealerHand = new Hand(List.of(
                new Card(Suit.DIAMOND, Denomination.THREE),
                new Card(Suit.HEART, Denomination.FIVE)));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.JACK));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.ACE));

        // when, then
        assertThat(player.calculateBettingResult(dealerHand)).isEqualTo(PlayerBettingResult.BLACKJACK);
    }
}
