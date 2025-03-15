package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Nested
    @DisplayName("일급 컬렉션 생성 테스트")
    class GenerateTest {

        @Test
        @DisplayName("8명 이상이면 생성 불가능하다")
        void cantGenerateOver8Players() {
            List<Player> over8Players = List.of(
                    new Player(new PlayerName("hula")),
                    new Player(new PlayerName("sana")),
                    new Player(new PlayerName("pppk")),
                    new Player(new PlayerName("iiif")),
                    new Player(new PlayerName("wilson")),
                    new Player(new PlayerName("hans")),
                    new Player(new PlayerName("duri")),
                    new Player(new PlayerName("hoddeok"))
            );

            assertThatThrownBy(() -> new Players(over8Players))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("플레이어가 없다면 생성 불가능하다")
        void cantGenerateEmpty() {
            List<Player> emptyPlayer = List.of();

            assertThatThrownBy(() -> new Players(emptyPlayer))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("2명에서 7명 이내면 정상적으로 생성한다")
        void generateBetween2to7Players() {
            List<Player> sevenPlayers = List.of(
                    new Player(new PlayerName("sana")),
                    new Player(new PlayerName("pppk")),
                    new Player(new PlayerName("iiif")),
                    new Player(new PlayerName("wilson")),
                    new Player(new PlayerName("hans")),
                    new Player(new PlayerName("duri"))
            );

            assertThatCode(() -> new Players(sevenPlayers))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("플레이어들의 베팅 금액 통계 테스트")
    class PlayersBetAmountTest {

        @Test
        @DisplayName("플레이어 베팅 금액을 모두 합산할 수 있다")
        void sumWinnings() {
            Player player1 = new Player(new PlayerName("hula"));
            player1.bet(1000);  // 승리 (블랙잭)
            player1.addCards(
                    new Card(Suit.SPADE, Denomination.ACE),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Player player2 = new Player(new PlayerName("sana"));
            player2.bet(1000);  // 승리
            player2.addCards(
                    new Card(Suit.SPADE, Denomination.QUEEN),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Player player3 = new Player(new PlayerName("pppk"));
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
            assertThat(players.calculateTotalPayout(dealer)).isEqualTo(new Payout(1500));
        }

        @Test
        @DisplayName("플레이어 베팅 금액을 모두 합산할 수 있다")
        void sumWinnings2() {
            Player player1 = new Player(new PlayerName("hula"));
            player1.bet(2000);  // 승리 (블랙잭)
            player1.addCards(
                    new Card(Suit.SPADE, Denomination.ACE),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Player player2 = new Player(new PlayerName("sana"));
            player2.bet(1000);  // 승리
            player2.addCards(
                    new Card(Suit.SPADE, Denomination.QUEEN),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Player player3 = new Player(new PlayerName("pppk"));
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
            assertThat(players.calculateTotalPayout(dealer)).isEqualTo(new Payout(-1000));
        }
    }
}
