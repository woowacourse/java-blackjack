package blackjack.player;

import static blackjackgame.Result.LOSE;
import static blackjackgame.Result.TIE;
import static blackjackgame.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjackgame.Result;
import card.Card;
import card.Rank;
import card.Suit;
import dto.PlayerWinningDto;
import participants.Dealer;
import participants.Name;
import participants.Player;
import participants.Players;

class PlayersTest {
    @DisplayName("생성할 수 있다")
    @Test
    void create() {
        assertThatCode(() -> new Players()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어 한 명을 추가한다.")
    void addPlayer() {
        Players players = new Players();
        Player player = new Player(new Name("로지"));
        players.add(player);
    }

    @DisplayName("인덱스에 해당하는 플레이어가 카드를 받는다.")
    @Test
    void takeCard() {
        Players players = new Players();
        Player player = new Player(new Name("폴로"));
        players.add(player);
        Card card = new Card(Rank.ACE, Suit.CLOVER);

        players.takeCard(0, card);

        assertThat(player.showCards()).contains(card);
    }

    @DisplayName("현재 플레이어의 인원수를 반환한다.")
    @Test
    void count() {
        Players players = new Players();
        Player player1 = new Player(new Name("폴로"));
        Player player2 = new Player(new Name("로지"));
        Player player3 = new Player(new Name("연어"));
        players.add(player1);
        players.add(player2);
        players.add(player3);

        assertThat(players.count()).isEqualTo(3);
    }

    @Test
    @DisplayName("인덱스에 해당하는 플레이어가 버스트인 경우 true를 반환한다.")
    void isBust() {
        Players players = new Players();
        Player player1 = new Player(new Name("폴로"));
        players.add(player1);
        player1.hit(new Card(Rank.KING, Suit.HEART));
        player1.hit(new Card(Rank.KING, Suit.DIAMOND));
        player1.hit(new Card(Rank.KING, Suit.SPADE));

        assertThat(players.isBust(0)).isTrue();
    }

    @Test
    @DisplayName("플레이어의 이름과 승패결과를 가져온다.")
    void getWinningResult() {
        Players players = new Players();
        Player player = new Player(new Name("폴로"));
        players.add(player);

        player.win();
        List<PlayerWinningDto> playerWinningResults = players.getWinningResults();
        PlayerWinningDto playerWinningDto = playerWinningResults.get(0);

        assertThat(playerWinningDto.getName().getValue()).isEqualTo("폴로");
        assertThat(playerWinningDto.getResult()).isEqualTo(WIN);
    }

    @Nested
    @DisplayName("승패를 계산하는 기능")
    class CalculateWinning {

        @Test
        @DisplayName("플레이어의 점수가 딜러의 점수보다 높고 플레이어가 버스트가 아니면 WIN을 반환한다.")
        void winWhenScoreIsHigher() {
            Players players = new Players();
            Player player = new Player(new Name("폴로"));
            players.add(player);

            Dealer dealer = new Dealer();
            player.hit(new Card(Rank.KING, Suit.HEART));
            dealer.hit(new Card(Rank.FIVE, Suit.CLOVER));

            players.calculateWinning(dealer);
            assertThat(player.getResult()).isEqualTo(WIN);
        }

        @Test
        @DisplayName("딜러가 버스트이고 플레이어가 버스트가 아니면 WIN을 반환한다.")
        void winWhenDealerBust() {
            Players players = new Players();
            Player player = new Player(new Name("폴로"));
            players.add(player);
            Dealer dealer = new Dealer();

            player.hit(new Card(Rank.KING, Suit.HEART));
            dealer.hit(new Card(Rank.FIVE, Suit.CLOVER));
            dealer.hit(new Card(Rank.KING, Suit.SPADE));
            dealer.hit(new Card(Rank.KING, Suit.CLOVER));
            players.calculateWinning(dealer);

            assertThat(player.getResult()).isEqualTo(WIN);
        }

        @Test
        @DisplayName("딜러보다 점수가 낮고 딜러가 버스트가 아니면 LOSE를 반환한다")
        void loseWhenLowerScore() {
            Players players = new Players();
            Player player = new Player(new Name("폴로"));
            players.add(player);
            Dealer dealer = new Dealer();

            player.hit(new Card(Rank.KING, Suit.HEART));
            dealer.hit(new Card(Rank.FIVE, Suit.CLOVER));
            dealer.hit(new Card(Rank.KING, Suit.SPADE));
            players.calculateWinning(dealer);

            assertThat(player.getResult()).isEqualTo(LOSE);
        }

        @Test
        @DisplayName("플레이어가 버스트이면 LOSE를 반환한다")
        void loseWhenPlayerBust() {
            Players players = new Players();
            Player player = new Player(new Name("폴로"));
            players.add(player);
            Dealer dealer = new Dealer();

            player.hit(new Card(Rank.KING, Suit.HEART));
            player.hit(new Card(Rank.JACK, Suit.CLOVER));
            player.hit(new Card(Rank.JACK, Suit.DIAMOND));
            dealer.hit(new Card(Rank.FIVE, Suit.CLOVER));
            dealer.hit(new Card(Rank.KING, Suit.SPADE));
            players.calculateWinning(dealer);

            assertThat(player.getResult()).isEqualTo(LOSE);
        }

        @Test
        @DisplayName("플레이어와 딜러의 점수가 같고 버스트가 아닌 경우 TIE를 반환한다.")
        void tieWhenSameScore() {
            Players players = new Players();
            Player player = new Player(new Name("폴로"));
            players.add(player);
            Dealer dealer = new Dealer();

            player.hit(new Card(Rank.KING, Suit.HEART));
            dealer.hit(new Card(Rank.KING, Suit.SPADE));
            players.calculateWinning(dealer);

            assertThat(player.getResult()).isEqualTo(TIE);
        }

        @Test
        @DisplayName("둘다 버스트인 경우는 플레이어가 패한다.")
        void bothBust() {
            Players players = new Players();
            Player player = new Player(new Name("폴로"));
            players.add(player);
            Dealer dealer = new Dealer();

            player.hit(new Card(Rank.KING, Suit.HEART));
            player.hit(new Card(Rank.JACK, Suit.CLOVER));
            player.hit(new Card(Rank.JACK, Suit.DIAMOND));
            dealer.hit(new Card(Rank.KING, Suit.CLOVER));
            dealer.hit(new Card(Rank.KING, Suit.SPADE));
            dealer.hit(new Card(Rank.QUEEN, Suit.SPADE));
            players.calculateWinning(dealer);

            assertThat(player.getResult()).isEqualTo(LOSE);
        }

        @Test
        @DisplayName("플레이어가 블랙잭으로 이긴경우 플레이어의 result는 BLACKJACK이다.")
        void winPlayerBlackjack() {
            Players players = new Players();
            Player player = new Player(new Name("폴로"));
            players.add(player);
            Dealer dealer = new Dealer();

            player.hit(new Card(Rank.KING, Suit.HEART));
            player.hit(new Card(Rank.ACE, Suit.HEART));
            dealer.hit(new Card(Rank.KING, Suit.CLOVER));
            dealer.hit(new Card(Rank.QUEEN, Suit.SPADE));
            dealer.hit(new Card(Rank.ACE, Suit.SPADE));
            players.calculateWinning(dealer);

            assertThat(player.getResult()).isEqualTo(Result.BLACKJACK);
        }

        @Test
        @DisplayName("딜러가 블랙잭으로 이긴경우 플레이어의 result는 LOSE 이다.")
        void winDealerBlackjack() {
            Players players = new Players();
            Player player = new Player(new Name("폴로"));
            players.add(player);
            Dealer dealer = new Dealer();

            player.hit(new Card(Rank.KING, Suit.CLOVER));
            player.hit(new Card(Rank.QUEEN, Suit.SPADE));
            player.hit(new Card(Rank.ACE, Suit.SPADE));
            dealer.hit(new Card(Rank.KING, Suit.HEART));
            dealer.hit(new Card(Rank.ACE, Suit.HEART));
            players.calculateWinning(dealer);

            assertThat(player.getResult()).isEqualTo(LOSE);
        }
    }
}
