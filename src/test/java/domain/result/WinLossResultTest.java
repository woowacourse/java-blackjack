package domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinLossResultTest {

    @Test
    @DisplayName("딜러보다 플레이어의 총합이 작으면 플레이어는 패배")
    void test1() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.TEN, Suit.CLUB));
        dealer.addCard(new Card(Denomination.TEN, Suit.DIAMOND));

        Player player = new Player("모루");
        player.addCard(new Card(Denomination.TEN, Suit.HEART));
        player.addCard(new Card(Denomination.NINE, Suit.SPADE));

        assertThat(WinLossResult.from(dealer, player)).isEqualTo(WinLossResult.LOSS);
    }

    @Test
    @DisplayName("딜러보다 플레이어의 총합이 크면 플레이어는 승리")
    void test2() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.TEN, Suit.CLUB));
        dealer.addCard(new Card(Denomination.NINE, Suit.DIAMOND));

        Player player = new Player("모루");
        player.addCard(new Card(Denomination.TEN, Suit.HEART));
        player.addCard(new Card(Denomination.TEN, Suit.SPADE));

        assertThat(WinLossResult.from(dealer, player)).isEqualTo(WinLossResult.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어의 총합이 같으면 무승부")
    void test3() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.TEN, Suit.CLUB));
        dealer.addCard(new Card(Denomination.NINE, Suit.DIAMOND));

        Player player = new Player("모루");
        player.addCard(new Card(Denomination.TEN, Suit.HEART));
        player.addCard(new Card(Denomination.NINE, Suit.SPADE));

        assertThat(WinLossResult.from(dealer, player)).isEqualTo(WinLossResult.DRAW);
    }

    @Test
    @DisplayName("딜러와 플레이어가 둘다 블랙잭이면 무승부")
    void test4() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.TEN, Suit.CLUB));
        dealer.addCard(new Card(Denomination.ACE, Suit.DIAMOND));

        Player player = new Player("모루");
        player.addCard(new Card(Denomination.TEN, Suit.HEART));
        player.addCard(new Card(Denomination.ACE, Suit.SPADE));

        assertThat(WinLossResult.from(dealer, player)).isEqualTo(WinLossResult.DRAW);
    }

    @Test
    @DisplayName("딜러와 플레이어의 총합이 21로 같은데, 플레이어만 블랙잭이면 플레이어 승리")
    void test5() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.TEN, Suit.CLUB));
        dealer.addCard(new Card(Denomination.NINE, Suit.DIAMOND));
        dealer.addCard(new Card(Denomination.TWO, Suit.DIAMOND));

        Player player = new Player("모루");
        player.addCard(new Card(Denomination.TEN, Suit.HEART));
        player.addCard(new Card(Denomination.ACE, Suit.SPADE));

        assertThat(WinLossResult.from(dealer, player)).isEqualTo(WinLossResult.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어의 총합이 21로 같은데, 딜러만 블랙잭이면 플레이어 패배")
    void test6() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.TEN, Suit.CLUB));
        dealer.addCard(new Card(Denomination.ACE, Suit.DIAMOND));

        Player player = new Player("모루");
        player.addCard(new Card(Denomination.TEN, Suit.CLUB));
        player.addCard(new Card(Denomination.NINE, Suit.DIAMOND));
        player.addCard(new Card(Denomination.TWO, Suit.DIAMOND));

        assertThat(WinLossResult.from(dealer, player)).isEqualTo(WinLossResult.LOSS);
    }
}
