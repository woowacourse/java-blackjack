package blackJack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Suit;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class WinOrLoseTest {

    private Dealer dealer = new Dealer();
    private Player player = new Player("rookie");

    @Nested
    @DisplayName("플레이어의 점수가 블랙잭이고")
    class playerIsBlackjack {
        @Test
        @DisplayName("딜러의 점수가 블랙잭인 경우 플레이어는 무승부이다.")
        void dealerIsBlackJackOfDraw() {
            player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.ACE));
            player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.JACK));
            dealer.receiveCard(Card.valueOf(Suit.HEART, Denomination.ACE));
            dealer.receiveCard(Card.valueOf(Suit.HEART, Denomination.JACK));

            assertThat(OutCome.ofJudge(dealer, player)).isEqualTo(OutCome.DRAW);
        }

        @Test
        @DisplayName("딜러의 점수가 21점인 경우 플레이어가 승리한다(블랙잭).")
        void dealerIs21ScoreOfBlackjack() {
            player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.ACE));
            player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.JACK));
            dealer.receiveCard(Card.valueOf(Suit.HEART, Denomination.THREE));
            dealer.receiveCard(Card.valueOf(Suit.HEART, Denomination.EIGHT));
            dealer.receiveCard(Card.valueOf(Suit.HEART, Denomination.JACK));

            assertThat(OutCome.ofJudge(dealer, player)).isEqualTo(OutCome.BLACKJACK);
        }

        @Test
        @DisplayName("딜러의 점수가 14점인 경우 플레이어가 승리한다(블랙잭).")
        void dealerIs14ScoreOfBlackjack() {
            player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.ACE));
            player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.JACK));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.ACE));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.THREE));

            assertThat(OutCome.ofJudge(dealer, player)).isEqualTo(OutCome.BLACKJACK);
        }
    }

    @Nested
    @DisplayName("딜러의 점수가 블랙잭이고")
    class dealerIsBlackjack {
        @Test
        @DisplayName("플레이어의 점수가 블랙잭인 경우 플레이어는 무승부이다.")
        void playerIsBlackjackOfDraw() {
            player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.ACE));
            player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.JACK));
            dealer.receiveCard(Card.valueOf(Suit.HEART, Denomination.ACE));
            dealer.receiveCard(Card.valueOf(Suit.HEART, Denomination.JACK));

            assertThat(OutCome.ofJudge(dealer, player)).isEqualTo(OutCome.DRAW);
        }

        @Test
        @DisplayName("플레이어의 점수가 21인 경우 플레이어가 패배한다.")
        void playerIs21ScoreOfLose() {
            player.receiveCard(Card.valueOf(Suit.HEART, Denomination.THREE));
            player.receiveCard(Card.valueOf(Suit.HEART, Denomination.EIGHT));
            player.receiveCard(Card.valueOf(Suit.HEART, Denomination.JACK));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.ACE));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.JACK));

            assertThat(OutCome.ofJudge(dealer, player)).isEqualTo(OutCome.LOSE);
        }

        @Test
        @DisplayName("플레이어의 점수가 14인 경우 플레이어가 패배한다.")
        void playerIs14ScoreOfLose() {
            player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.ACE));
            player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.THREE));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.ACE));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.JACK));

            assertThat(OutCome.ofJudge(dealer, player)).isEqualTo(OutCome.LOSE);
        }
    }

    @Nested
    @DisplayName("플레이어가 버스트이고")
    class playerIsBust {
        @Test
        @DisplayName("딜러가 버스트인 경우 플레이어가 패배한다.")
        void dealerIsBustOfLose() {
            player.receiveCard(Card.valueOf(Suit.HEART, Denomination.KING));
            player.receiveCard(Card.valueOf(Suit.HEART, Denomination.EIGHT));
            player.receiveCard(Card.valueOf(Suit.HEART, Denomination.JACK));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.TEN));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.SIX));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.KING));

            assertThat(OutCome.ofJudge(dealer, player)).isEqualTo(OutCome.LOSE);
        }

        @Test
        @DisplayName("딜러의 점수가 20인 경우 플레이어가 패배한다.")
        void dealerIs21ScoreOfLose() {
            player.receiveCard(Card.valueOf(Suit.HEART, Denomination.KING));
            player.receiveCard(Card.valueOf(Suit.HEART, Denomination.EIGHT));
            player.receiveCard(Card.valueOf(Suit.HEART, Denomination.JACK));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.TEN));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.JACK));

            assertThat(OutCome.ofJudge(dealer, player)).isEqualTo(OutCome.LOSE);
        }
    }

    @Nested
    @DisplayName("딜러가 버스트이고")
    class dealerIsBust {
        @Test
        @DisplayName("플레이어가 버스트인 경우 플레이어가 패배한다.")
        void playerIsBustOfLose() {
            player.receiveCard(Card.valueOf(Suit.HEART, Denomination.KING));
            player.receiveCard(Card.valueOf(Suit.HEART, Denomination.EIGHT));
            player.receiveCard(Card.valueOf(Suit.HEART, Denomination.JACK));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.TEN));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.SIX));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.KING));

            assertThat(OutCome.ofJudge(dealer, player)).isEqualTo(OutCome.LOSE);
        }
        @Test
        @DisplayName("플레이어의 점수가 20인 경우 플레이어가 승리한다.")
        void playerIs20ScoreOfWin() {
            player.receiveCard(Card.valueOf(Suit.HEART, Denomination.KING));
            player.receiveCard(Card.valueOf(Suit.HEART, Denomination.TEN));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.TEN));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.SIX));
            dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.KING));

            assertThat(OutCome.ofJudge(dealer, player)).isEqualTo(OutCome.WIN);
        }
    }

    @Test
    @DisplayName("플레이어의 점수가 20, 딜러의 점수가 14인 경우 플레이어가 승리한다.")
    void calculatePlayerScoreWin() {
        player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.TEN));
        player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.JACK));
        dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.ACE));
        dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.THREE));

        assertThat(OutCome.ofJudge(dealer, player)).isEqualTo(OutCome.WIN);
    }

    @Test
    @DisplayName("플레이어의 점수가 14, 딜러의 점수가 20인 경우 플레이어가 패배한다.")
    void calculatePlayerScoreLose() {
        player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.ACE));
        player.receiveCard(Card.valueOf(Suit.SPADE, Denomination.THREE));
        dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.TEN));
        dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.JACK));

        assertThat(OutCome.ofJudge(dealer, player)).isEqualTo(OutCome.LOSE);
    }
}
