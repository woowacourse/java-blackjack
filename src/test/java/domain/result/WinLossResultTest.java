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
}
