package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("Player 는 핸드의 총합값을 반환할 수 있다")
    void test1() {
        Player player = new Player("모루");
        player.addCard(new Card(Denomination.TWO, Suit.CLUB));
        player.addCard(new Card(Denomination.FOUR, Suit.CLUB));
        player.addCard(new Card(Denomination.FIVE, Suit.DIAMOND));

        assertThat(player.getHandTotal()).isEqualTo(11);
    }

    @Nested
    @DisplayName("Player는 딜러와의 총합을 비교하여 승무패를 반환할 수 있다.")
    class GetWinLossTest {
        @Test
        @DisplayName("Player는 딜러와의 총합을 비교하여 승무패를 반환할 수 있다. - 승")
        void test2() {
            Player player = new Player("모루");
            player.addCard(new Card(Denomination.TWO, Suit.CLUB));
            player.addCard(new Card(Denomination.TEN, Suit.CLUB));
            player.addCard(new Card(Denomination.FIVE, Suit.DIAMOND));

            Dealer dealer = new Dealer();
            dealer.addCard(new Card(Denomination.TWO, Suit.CLUB));
            dealer.addCard(new Card(Denomination.ACE, Suit.CLUB));

            assertThat(player.getWinLoss(dealer.getHandTotal())).isEqualTo(1);
        }

        @Test
        @DisplayName("Player는 딜러와의 총합을 비교하여 승무패를 반환할 수 있다. - 패")
        void test3() {
            Player player = new Player("모루");
            player.addCard(new Card(Denomination.TWO, Suit.CLUB));
            player.addCard(new Card(Denomination.TEN, Suit.CLUB));
            player.addCard(new Card(Denomination.FIVE, Suit.CLUB));

            Dealer dealer = new Dealer();
            dealer.addCard(new Card(Denomination.TWO, Suit.DIAMOND));
            dealer.addCard(new Card(Denomination.ACE, Suit.DIAMOND));
            dealer.addCard(new Card(Denomination.EIGHT, Suit.DIAMOND));

            assertThat(player.getWinLoss(dealer.getHandTotal())).isEqualTo(-1);
        }

        @Test
        @DisplayName("Player는 딜러와의 총합을 비교하여 승무패를 반환할 수 있다. - 무승부")
        void test4() {
            Player player = new Player("모루");
            player.addCard(new Card(Denomination.TWO, Suit.CLUB));
            player.addCard(new Card(Denomination.TEN, Suit.CLUB));
            player.addCard(new Card(Denomination.FIVE, Suit.CLUB));

            Dealer dealer = new Dealer();
            dealer.addCard(new Card(Denomination.TWO, Suit.DIAMOND));
            dealer.addCard(new Card(Denomination.TEN, Suit.DIAMOND));
            dealer.addCard(new Card(Denomination.FIVE, Suit.DIAMOND));

            assertThat(player.getWinLoss(dealer.getHandTotal())).isEqualTo(0);
        }

        @Test
        @DisplayName("Player는 딜러와의 총합을 비교하여 승무패를 반환할 수 있다. - 플레이어 버스트 시 패배")
        void test5() {
            Player player = new Player("모루");
            player.addCard(new Card(Denomination.TWO, Suit.CLUB));
            player.addCard(new Card(Denomination.TEN, Suit.CLUB));
            player.addCard(new Card(Denomination.TEN, Suit.CLUB));

            Dealer dealer = new Dealer();
            dealer.addCard(new Card(Denomination.TWO, Suit.DIAMOND));
            dealer.addCard(new Card(Denomination.TEN, Suit.DIAMOND));
            dealer.addCard(new Card(Denomination.FIVE, Suit.DIAMOND));

            assertThat(player.getWinLoss(dealer.getHandTotal())).isEqualTo(-1);
        }
    }

}
