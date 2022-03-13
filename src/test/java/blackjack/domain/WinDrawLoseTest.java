package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinDrawLoseTest {

    @Test
    @DisplayName("딜러가 버스트이고 게스트가 버스트가 아닐 떄 딜러가 패배하는지 확인")
    void dealerBustTest() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.JACK, Suit.CLUBS));
        dealer.hit(new Card(Denomination.JACK, Suit.HEARTS));
        dealer.hit(new Card(Denomination.JACK, Suit.SPADES));
        Guest guest = new Guest("a", (p) -> HitFlag.Y);
        guest.hit(new Card(Denomination.ACE, Suit.CLUBS));
        guest.hit(new Card(Denomination.FIVE, Suit.CLUBS));
        assertThat(WinDrawLose.judgeDealerWinDrawLose(dealer, guest))
                .isEqualTo(WinDrawLose.LOSE);
    }

    @Test
    @DisplayName("게스트가 버스트일 때 딜러가 승리하는지 확인")
    void guestBustTest() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.SEVEN, Suit.CLUBS));
        dealer.hit(new Card(Denomination.TEN, Suit.HEARTS));
        Guest guest = new Guest("a", (p) -> HitFlag.Y);
        guest.hit(new Card(Denomination.TEN, Suit.SPADES));
        guest.hit(new Card(Denomination.JACK, Suit.CLUBS));
        guest.hit(new Card(Denomination.FIVE, Suit.CLUBS));
        assertThat(WinDrawLose.judgeDealerWinDrawLose(dealer, guest))
                .isEqualTo(WinDrawLose.WIN);
    }

    @Test
    @DisplayName("게스트가 버스트일 경우 딜러가 버스트여도 승리하는지 확인")
    void guestBustTestAndDealerBust() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.JACK, Suit.CLUBS));
        dealer.hit(new Card(Denomination.JACK, Suit.HEARTS));
        dealer.hit(new Card(Denomination.JACK, Suit.SPADES));
        Guest guest = new Guest("a", (p) -> HitFlag.Y);
        guest.hit(new Card(Denomination.KING, Suit.CLUBS));
        guest.hit(new Card(Denomination.KING, Suit.HEARTS));
        guest.hit(new Card(Denomination.KING, Suit.SPADES));
        assertThat(WinDrawLose.judgeDealerWinDrawLose(dealer, guest))
                .isEqualTo(WinDrawLose.WIN);
    }

    @Test
    @DisplayName("딜러가 블랙잭일 때 게스트가 블랙잭이면 무승부가 나는지 확인")
    void dealerBlackjackAndGuestBlackjack() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.JACK, Suit.CLUBS));
        dealer.hit(new Card(Denomination.ACE, Suit.HEARTS));
        Guest guest = new Guest("a", (p) -> HitFlag.Y);
        guest.hit(new Card(Denomination.ACE, Suit.CLUBS));
        guest.hit(new Card(Denomination.KING, Suit.HEARTS));
        assertThat(WinDrawLose.judgeDealerWinDrawLose(dealer, guest))
                .isEqualTo(WinDrawLose.DRAW);
    }

    @Test
    @DisplayName("블랙잭이 21점을 이기게 판정하는지 확인")
    void blackjackWin21() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.JACK, Suit.CLUBS));
        dealer.hit(new Card(Denomination.ACE, Suit.HEARTS));
        Guest guest = new Guest("a", (p) -> HitFlag.Y);
        guest.hit(new Card(Denomination.ACE, Suit.CLUBS));
        guest.hit(new Card(Denomination.KING, Suit.HEARTS));
        guest.hit(new Card(Denomination.QUEEN, Suit.HEARTS));
        assertThat(WinDrawLose.judgeDealerWinDrawLose(dealer, guest))
                .isEqualTo(WinDrawLose.WIN);
    }

    @Test
    @DisplayName("버스트와 블랙잭이 없을 때 점수 비교해서 승패 판단하는지 확인 - 딜러 승")
    void noEventDealerWin() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.JACK, Suit.CLUBS));
        dealer.hit(new Card(Denomination.TEN, Suit.HEARTS));
        Guest guest = new Guest("a", (p) -> HitFlag.Y);
        guest.hit(new Card(Denomination.NINE, Suit.HEARTS));
        guest.hit(new Card(Denomination.QUEEN, Suit.HEARTS));
        assertThat(WinDrawLose.judgeDealerWinDrawLose(dealer, guest))
                .isEqualTo(WinDrawLose.WIN);
    }

    @Test
    @DisplayName("버스트와 블랙잭이 없을 때 점수 비교해서 승패 판단하는지 확인 - 게스트 승")
    void noEventGuestWin() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.KING, Suit.CLUBS));
        dealer.hit(new Card(Denomination.NINE, Suit.HEARTS));
        Guest guest = new Guest("a", (p) -> HitFlag.Y);
        guest.hit(new Card(Denomination.JACK, Suit.CLUBS));
        guest.hit(new Card(Denomination.QUEEN, Suit.HEARTS));
        assertThat(WinDrawLose.judgeDealerWinDrawLose(dealer, guest))
                .isEqualTo(WinDrawLose.LOSE);
    }

    @Test
    @DisplayName("버스트와 블랙잭이 없을 때 점수 비교해서 승패 판단하는지 확인 - 무승부")
    void noEventDraw() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.KING, Suit.CLUBS));
        dealer.hit(new Card(Denomination.NINE, Suit.HEARTS));
        Guest guest = new Guest("a", (p) -> HitFlag.Y);
        guest.hit(new Card(Denomination.JACK, Suit.CLUBS));
        guest.hit(new Card(Denomination.NINE, Suit.SPADES));
        assertThat(WinDrawLose.judgeDealerWinDrawLose(dealer, guest))
                .isEqualTo(WinDrawLose.DRAW);
    }
}
