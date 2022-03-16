package blackJack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackJack.domain.card.Card;
import blackJack.domain.card.Cards;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Suit;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinOrLoseTest {

    private Dealer dealer = new Dealer();
    private Player player = new Player("rookie");

    @Test
    @DisplayName("플레이어의 점수가 블랙잭, 딜러의 점수가 14라 플레이어가 승리하는 경우")
    void calculatePlayerBlackJackWin() {
        Cards playerCards = new Cards(
                Set.of(Card.valueOf(Suit.SPADE, Denomination.ACE), Card.valueOf(Suit.SPADE, Denomination.JACK)));
        Cards dealerCards = new Cards(
                Set.of(Card.valueOf(Suit.SPADE, Denomination.ACE), Card.valueOf(Suit.SPADE, Denomination.THREE)));

        assertThat(OutCome.of(dealerCards, playerCards)).isEqualTo(OutCome.BLACKJACK);
    }

    @Test
    @DisplayName("플레이어의 점수가 20, 딜러의 점수가 14여서 플레이어가 승리하는 경우")
    void calculatePlayerScoreWin() {
        Cards playerCards = new Cards(
                Set.of(Card.valueOf(Suit.SPADE, Denomination.TEN), Card.valueOf(Suit.SPADE, Denomination.JACK)));
        Cards dealerCards = new Cards(
                Set.of(Card.valueOf(Suit.SPADE, Denomination.ACE), Card.valueOf(Suit.SPADE, Denomination.THREE)));

        assertThat(OutCome.of(dealerCards, playerCards)).isEqualTo(OutCome.WIN);
    }

    @Test
    @DisplayName("플레이어의 점수가 14, 딜러의 점수가 블랙잭이라 플레이가 패배하는 경우")
    void calculateDealerBlackJackLose() {
        Cards playerCards = new Cards(
                Set.of(Card.valueOf(Suit.SPADE, Denomination.ACE), Card.valueOf(Suit.SPADE, Denomination.THREE)));
        Cards dealerCards = new Cards(
                Set.of(Card.valueOf(Suit.SPADE, Denomination.ACE), Card.valueOf(Suit.SPADE, Denomination.JACK)));

        assertThat(OutCome.of(dealerCards, playerCards)).isEqualTo(OutCome.LOSE);
    }

    @Test
    @DisplayName("플레이어의 점수가 14, 딜러의 점수가 20여서 플레이어가 패배하는 경우")
    void calculatePlayerScoreLose() {
        Cards playerCards = new Cards(
                Set.of(Card.valueOf(Suit.SPADE, Denomination.ACE), Card.valueOf(Suit.SPADE, Denomination.THREE)));
        Cards dealerCards = new Cards(
                Set.of(Card.valueOf(Suit.SPADE, Denomination.ACE), Card.valueOf(Suit.SPADE, Denomination.JACK)));

        assertThat(OutCome.of(dealerCards, playerCards)).isEqualTo(OutCome.LOSE);
    }

    @Test
    @DisplayName("플레이어가 블랙잭, 딜러도 블랙잭이여서 플레이어가 무승부 경우")
    void calculatePlayerAndDealerScoreDraw() {
        Cards playerCards = new Cards(
                Set.of(Card.valueOf(Suit.SPADE, Denomination.ACE), Card.valueOf(Suit.SPADE, Denomination.JACK)));
        Cards dealerCards = new Cards(
                Set.of(Card.valueOf(Suit.HEART, Denomination.ACE), Card.valueOf(Suit.HEART, Denomination.JACK)));

        assertThat(OutCome.of(dealerCards, playerCards)).isEqualTo(OutCome.DRAW);
    }

    @Test
    @DisplayName("플레이어가 블랙잭, 딜러의 점수가 21점이여서 플레이어가 승리하는 경우")
    void calculatePlayerBlackJackAndDealerScoreWin() {
        Cards playerCards = new Cards(
                Set.of(Card.valueOf(Suit.SPADE, Denomination.ACE), Card.valueOf(Suit.SPADE, Denomination.JACK)));
        Cards dealerCards = new Cards(
                Set.of(Card.valueOf(Suit.HEART, Denomination.THREE),
                        Card.valueOf(Suit.HEART, Denomination.EIGHT),
                        Card.valueOf(Suit.HEART, Denomination.JACK)));

        assertThat(OutCome.of(dealerCards, playerCards)).isEqualTo(OutCome.BLACKJACK);
    }

    @Test
    @DisplayName("플레이어가 21, 딜러가 블랙잭 이여서 플레이어가 패배하는 경우")
    void calculatePlayerScoreAndDealerBlackJackLose() {
        Cards playerCards = new Cards(
                Set.of(Card.valueOf(Suit.HEART, Denomination.THREE),
                        Card.valueOf(Suit.HEART, Denomination.EIGHT),
                        Card.valueOf(Suit.HEART, Denomination.JACK)));

        Cards dealerCards = new Cards(
                Set.of(Card.valueOf(Suit.SPADE, Denomination.ACE),
                        Card.valueOf(Suit.SPADE, Denomination.JACK)));

        assertThat(OutCome.of(dealerCards, playerCards)).isEqualTo(OutCome.LOSE);
    }

    @Test
    @DisplayName("플레이어가 버스트, 딜러의 점수가 20점이여서 플레이어가 패배하는 경우")
    void calculatePlayerBustAndDealerScoreLose() {
        Cards playerCards = new Cards(
                Set.of(Card.valueOf(Suit.HEART, Denomination.KING),
                        Card.valueOf(Suit.HEART, Denomination.EIGHT),
                        Card.valueOf(Suit.HEART, Denomination.JACK)));

        Cards dealerCards = new Cards(
                Set.of(Card.valueOf(Suit.SPADE, Denomination.TEN),
                        Card.valueOf(Suit.SPADE, Denomination.JACK)));

        assertThat(OutCome.of(dealerCards, playerCards)).isEqualTo(OutCome.LOSE);
    }

    @Test
    @DisplayName("플레이어의 점수가 20점, 딜러가 버스트여서 플레이어가 승리하는 경우")
    void calculatePlayerScoreAndDealerBustWinner() {
        Cards playerCards = new Cards(
                Set.of(Card.valueOf(Suit.HEART, Denomination.KING),
                        Card.valueOf(Suit.HEART, Denomination.TEN)));

        Cards dealerCards = new Cards(
                Set.of(Card.valueOf(Suit.SPADE, Denomination.TEN),
                        Card.valueOf(Suit.SPADE, Denomination.SIX),
                        Card.valueOf(Suit.SPADE, Denomination.KING)));

        assertThat(OutCome.of(dealerCards, playerCards)).isEqualTo(OutCome.WIN);
    }

    @Test
    @DisplayName("플레이어가 버스트, 딜러도 버스트여서 플레이어가 패배하는 경우")
    void calculatePlayerBustAndDealerBustLose() {
        Cards playerCards = new Cards(
                Set.of(Card.valueOf(Suit.HEART, Denomination.KING),
                        Card.valueOf(Suit.HEART, Denomination.EIGHT),
                        Card.valueOf(Suit.HEART, Denomination.JACK)));

        Cards dealerCards = new Cards(
                Set.of(Card.valueOf(Suit.SPADE, Denomination.TEN),
                        Card.valueOf(Suit.SPADE, Denomination.SIX),
                        Card.valueOf(Suit.SPADE, Denomination.KING)));

        assertThat(OutCome.of(dealerCards, playerCards)).isEqualTo(OutCome.LOSE);
    }
}
