package blackjackGame;

import static blackjackGame.Result.LOSE;
import static blackjackGame.Result.TIE;
import static blackjackGame.Result.WIN;
import static blackjackGame.Result.calculateWinning;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import card.Card;
import card.CardNumber;
import card.Pattern;
import player.Dealer;
import player.Name;
import player.Player;

class ResultTest {
    @Nested
    @DisplayName("승패를 계산하는 기능")
    class CalculateWinning {

        @Test
        @DisplayName("플레이어의 점수가 딜러의 점수보다 높고 플레이어가 버스트가 아니면 WIN을 반환한다.")
        void winWhenScoreIsHigher() {
            Player player = new Player(new Name("폴로"));
            Dealer dealer = new Dealer();
            player.hit(new Card(CardNumber.KING, Pattern.HEART));
            dealer.hit(new Card(CardNumber.FIVE, Pattern.CLOVER));

            assertThat(calculateWinning(player, dealer)).isEqualTo(WIN);
        }

        @Test
        @DisplayName("딜러가 버스트이고 플레이어가 버스트가 아니면 WIN을 반환한다.")
        void winWhenDealerBust() {
            Player player = new Player(new Name("폴로"));
            Dealer dealer = new Dealer();
            player.hit(new Card(CardNumber.KING, Pattern.HEART));
            dealer.hit(new Card(CardNumber.FIVE, Pattern.CLOVER));
            dealer.hit(new Card(CardNumber.KING, Pattern.SPADE));
            dealer.hit(new Card(CardNumber.KING, Pattern.CLOVER));

            assertThat(calculateWinning(player, dealer)).isEqualTo(WIN);
        }

        @Test
        @DisplayName("딜러보다 점수가 낮고 딜러가 버스트가 아니면 LOSE를 반환한다")
        void loseWhenLowerScore() {
            Player player = new Player(new Name("폴로"));
            Dealer dealer = new Dealer();
            player.hit(new Card(CardNumber.KING, Pattern.HEART));
            dealer.hit(new Card(CardNumber.FIVE, Pattern.CLOVER));
            dealer.hit(new Card(CardNumber.KING, Pattern.SPADE));

            assertThat(calculateWinning(player, dealer)).isEqualTo(LOSE);
        }

        @Test
        @DisplayName("플레이어가 버스트이면 LOSE를 반환한다")
        void loseWhenPlayerBust() {
            Player player = new Player(new Name("폴로"));
            Dealer dealer = new Dealer();
            player.hit(new Card(CardNumber.KING, Pattern.HEART));
            player.hit(new Card(CardNumber.JACK, Pattern.CLOVER));
            player.hit(new Card(CardNumber.JACK, Pattern.DIAMOND));
            dealer.hit(new Card(CardNumber.FIVE, Pattern.CLOVER));
            dealer.hit(new Card(CardNumber.KING, Pattern.SPADE));

            assertThat(calculateWinning(player, dealer)).isEqualTo(LOSE);
        }

        @Test
        @DisplayName("플레이어와 딜러의 점수가 같고 버스트가 아닌 경우 TIE를 반환한다.")
        void tieWhenSameScore() {
            Player player = new Player(new Name("폴로"));
            Dealer dealer = new Dealer();
            player.hit(new Card(CardNumber.KING, Pattern.HEART));
            dealer.hit(new Card(CardNumber.KING, Pattern.SPADE));

            assertThat(calculateWinning(player, dealer)).isEqualTo(TIE);
        }


    }
}
