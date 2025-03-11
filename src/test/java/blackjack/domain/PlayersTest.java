package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Nested
public class PlayersTest {

    @Nested
    @DisplayName("플레이어들의 베팅 금액 통계 테스트")
    class PlayersBetAmountTest {

        @Test
        @DisplayName("플레이어 베팅 금액을 모두 합산할 수 있다")
        void sumWinnings() {
            Player player1 = new Player("hula");
            player1.bet(1000);  // 승리 (블랙잭)
            player1.addCards(
                    new Card(Suit.SPADE, Denomination.ACE),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Player player2 = new Player("sana");
            player2.bet(1000);  // 승리
            player2.addCards(
                    new Card(Suit.SPADE, Denomination.QUEEN),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Player player3 = new Player("pppk");
            player3.bet(1000);  // 패배 (버스트)
            player3.addCards(
                    new Card(Suit.HEART, Denomination.TWO),
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Dealer dealer = new Dealer();
            dealer.addCards(
                    new Card(Suit.CLUB, Denomination.EIGHT),
                    new Card(Suit.SPADE, Denomination.ACE)
            );

            Players players = new Players(List.of(player1, player2, player3));
            assertThat(players.calculateTotalPayout(dealer)).isEqualTo(1500);
        }

        @Test
        @DisplayName("플레이어 베팅 금액을 모두 합산할 수 있다")
        void sumWinnings2() {
            Player player1 = new Player("hula");
            player1.bet(2000);  // 승리 (블랙잭)
            player1.addCards(
                    new Card(Suit.SPADE, Denomination.ACE),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Player player2 = new Player("sana");
            player2.bet(1000);  // 승리
            player2.addCards(
                    new Card(Suit.SPADE, Denomination.QUEEN),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Player player3 = new Player("pppk");
            player3.bet(5000);  // 패배 (버스트)
            player3.addCards(
                    new Card(Suit.HEART, Denomination.TWO),
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Dealer dealer = new Dealer();
            dealer.addCards(
                    new Card(Suit.CLUB, Denomination.EIGHT),
                    new Card(Suit.SPADE, Denomination.ACE)
            );

            Players players = new Players(List.of(player1, player2, player3));
            assertThat(players.calculateTotalPayout(dealer)).isEqualTo(-1000);
        }
    }
}
