package referee;

import card.Card;
import card.CardNumber;
import card.CardPattern;
import dealer.Dealer;
import gameResult.GameResult;
import gameResult.PlayerBlackJack;
import gameResult.PlayerBust;
import gameResult.PlayerLose;
import gameResult.PlayerPush;
import gameResult.PlayerWin;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import player.Player;

public class RefereeTest {

    @Nested
    @DisplayName("플레이어가 블랙잭인 경우 테스트")
    class PlayerIsBlackJack {

        private Player player;

        @BeforeEach
        void setUp() {
            player = Player.joinGame("pola", List.of(new Card(CardNumber.ACE, CardPattern.DIA_PATTERN),
                    new Card(CardNumber.JACK, CardPattern.SPADE_PATTERN)), 10000);
        }

        @DisplayName("딜러도 BlackJack인 경우 무승부로 간주한다.")
        @Test
        void playerDealerBothBlackJack() {
            Dealer dealer = new Dealer(List.of(new Card(CardNumber.ACE, CardPattern.HEART_PATTERN),
                    new Card(CardNumber.JACK, CardPattern.DIA_PATTERN)));

            Referee referee = new Referee();
            GameResult result = referee.judge(player, dealer);

            Assertions.assertThat(result)
                    .isInstanceOf(PlayerPush.class);
        }

        @DisplayName("딜러는 BlackJack이 아닌 경우 Player가 우승한 것으로 간주한다.")
        @Test
        void dealerNotBlackJack() {
            Dealer dealer = new Dealer(List.of(new Card(CardNumber.ACE, CardPattern.HEART_PATTERN),
                    new Card(CardNumber.SEVEN, CardPattern.DIA_PATTERN)));

            Referee referee = new Referee();
            GameResult result = referee.judge(player, dealer);

            Assertions.assertThat(result)
                    .isInstanceOf(PlayerBlackJack.class);
        }
    }

    @Nested
    @DisplayName("플레이어가 Bust인 경우")
    class PlayerIsBust {

        @DisplayName("딜러도 Bust이더라도 플레이어가 진다.")
        @Test
        void dealerAndPlayerBust() {
            Player player = Player.joinGame("pola", List.of(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN),
                    new Card(CardNumber.TEN, CardPattern.SPADE_PATTERN),
                    new Card(CardNumber.JACK, CardPattern.DIA_PATTERN)), 10000);

            Dealer dealer = new Dealer(List.of(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN),
                    new Card(CardNumber.TEN, CardPattern.SPADE_PATTERN),
                    new Card(CardNumber.JACK, CardPattern.DIA_PATTERN),
                    new Card(CardNumber.JACK, CardPattern.DIA_PATTERN)));

            Referee referee = new Referee();
            GameResult result = referee.judge(player, dealer);

            Assertions.assertThat(result)
                    .isInstanceOf(PlayerBust.class);
        }
    }

    @Nested
    @DisplayName("플레이어가 어떤 상태를 가지고 있지 않다.")
    class PlayerWithNoStatus {

        private Player player;

        @BeforeEach
        void SetUp() {
            player = Player.joinGame("pola", List.of(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN),
                    new Card(CardNumber.NINE, CardPattern.SPADE_PATTERN)), 10000);
        }

        @DisplayName("플레이어의 점수가 딜러보다 낮다")
        @Test
        void playerIsOverDealerScore() {
            Dealer dealer = new Dealer(List.of(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN),
                    new Card(CardNumber.TEN, CardPattern.SPADE_PATTERN)));

            Referee referee = new Referee();
            GameResult result = referee.judge(player, dealer);

            Assertions.assertThat(result)
                    .isInstanceOf(PlayerLose.class);
        }

        @DisplayName("플레이어의 점수가 딜러보다 높다")
        @Test
        void playerIsNotOverDealerScore() {
            Dealer dealer = new Dealer(List.of(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN),
                    new Card(CardNumber.SEVEN, CardPattern.SPADE_PATTERN)));

            Referee referee = new Referee();
            GameResult result = referee.judge(player, dealer);

            Assertions.assertThat(result)
                    .isInstanceOf(PlayerWin.class);
        }

        @DisplayName("플레이어의 점수와 딜러의 점수가 같다")
        @Test
        void playerIsSameWithDealerScore() {
            Dealer dealer = new Dealer(List.of(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN),
                    new Card(CardNumber.NINE, CardPattern.HEART_PATTERN)));

            Referee referee = new Referee();
            GameResult result = referee.judge(player, dealer);

            Assertions.assertThat(result)
                    .isInstanceOf(PlayerPush.class);
        }
    }
}
