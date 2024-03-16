package blackjack.model.result;

import static blackjack.model.deck.Score.ACE;
import static blackjack.model.deck.Score.FIVE;
import static blackjack.model.deck.Score.FOUR;
import static blackjack.model.deck.Score.NINE;
import static blackjack.model.deck.Score.SEVEN;
import static blackjack.model.deck.Score.TEN;
import static blackjack.model.deck.Score.THREE;
import static blackjack.model.deck.Score.TWO;
import static blackjack.model.deck.Shape.CLOVER;
import static blackjack.model.deck.Shape.DIA;
import static blackjack.model.deck.Shape.HEART;
import static blackjack.model.deck.Shape.SPADE;
import static blackjack.model.result.ResultCommand.BLACK_JACK_WIN;
import static blackjack.model.result.ResultCommand.DRAW;
import static blackjack.model.result.ResultCommand.LOSE;
import static blackjack.model.result.ResultCommand.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.deck.Card;
import blackjack.model.deck.Deck;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RefereeTest {
    @Nested
    @DisplayName("딜러 카드의 총 합이 21일 미만일 때 플레이어의 결과: ")
    class DealerTotalScoreIsUnder21 {

        @Test
        @DisplayName("카드의 총 합이 딜러보다 크면 플레이어가 승리한다.")
        void playerWinWhenBiggerThanDealer() {
            Player player = Fixtures.createPlayer();
            Dealer dealer = Fixtures.createDealer();
            Referee referee = Fixtures.createReferee(dealer);

            player.receiveInitialCards(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN)));
            dealer.receiveInitialCards(List.of(new Card(SPADE, SEVEN), new Card(DIA, TEN)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(WIN);
        }

        @Test
        @DisplayName("카드의 총 합이 딜러와 동일하지만 카드 수는 적은 경우 플레이어가 승리한다.")
        void playerWinWhenHasLittleCardsThanDealer() {
            Player player = Fixtures.createPlayer();
            Dealer dealer = Fixtures.createDealer();
            Referee referee = Fixtures.createReferee(dealer);

            player.receiveInitialCards(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN)));
            dealer.receiveInitialCards(List.of(new Card(SPADE, SEVEN), new Card(DIA, TEN), new Card(DIA, THREE)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(WIN);
        }
    }

    @Nested
    @DisplayName("딜러 카드의 총 합이 21일때 플레이어의 결과: ")
    class DealerTotalScoreIs21 {

        @Test
        @DisplayName("플레이어 카드만 블랙잭인 경우 플레이어가 블랙잭 승리한다.")
        void playerBlackJackWinWhenOnlyPlayerBlackJack() {
            Player player = Fixtures.createPlayer();
            Dealer dealer = Fixtures.createDealer();
            Referee referee = Fixtures.createReferee(dealer);

            player.receiveInitialCards(List.of(new Card(CLOVER, TEN), new Card(SPADE, ACE)));
            dealer.receiveInitialCards(List.of(new Card(SPADE, SEVEN), new Card(DIA, TEN), new Card(DIA, FOUR)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(BLACK_JACK_WIN);
        }

        @Test
        @DisplayName("플레이어도 21이면 플레이어의 카드 수가 딜러의 카드 수보다 적은 경우 플레이어가 승리한다.")
        void playerWinWhenPlayerScoreIs21ButHasLittleCardsThanDealer() {
            Player player = Fixtures.createPlayer();
            Dealer dealer = Fixtures.createDealer();
            Referee referee = Fixtures.createReferee(dealer);

            player.receiveInitialCards(List.of(new Card(CLOVER, TEN), new Card(CLOVER, NINE), new Card(SPADE, TWO)));
            dealer.receiveInitialCards(
                    List.of(new Card(SPADE, SEVEN), new Card(DIA, FIVE), new Card(CLOVER, FIVE), new Card(DIA, FOUR)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(WIN);
        }
    }

    @Nested
    @DisplayName("딜러의 총 점수가 21을 초과하면(버스트)")
    class DealerTotalScoreIsOver21 {

        @Test
        @DisplayName("플레이어 결과가 21이하인 경우 플레이어가 승리한다.")
        void playerWinWhenOnlyPlayerNotBust() {
            Player player = Fixtures.createPlayer();
            Dealer dealer = Fixtures.createDealer();
            Referee referee = Fixtures.createReferee(dealer);

            player.receiveInitialCards(List.of(new Card(CLOVER, TEN), new Card(SPADE, FOUR)));
            dealer.receiveInitialCards(List.of(new Card(SPADE, FOUR), new Card(DIA, TEN), new Card(DIA, TEN)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(WIN);
        }

        @Test
        @DisplayName("플레이어가 21을 초과하는 경우 플레이어가 패배한다.")
        void playerLoseWhenBust() {
            Player player = Fixtures.createPlayer();
            Dealer dealer = Fixtures.createDealer();
            Referee referee = Fixtures.createReferee(dealer);

            player.receiveInitialCards(List.of(new Card(CLOVER, TEN), new Card(SPADE, FOUR), new Card(CLOVER, TEN)));
            dealer.receiveInitialCards(List.of(new Card(SPADE, FOUR), new Card(DIA, TEN), new Card(DIA, TEN)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(LOSE);
        }
    }

    @Nested
    @DisplayName("무승부 조건: ")
    class Draw {

        @Test
        @DisplayName("플레이어와 딜러 모두 블랙잭이면 무승부이다.")
        void bothBlackJack() {
            Player player = Fixtures.createPlayer();
            Dealer dealer = Fixtures.createDealer();
            Referee referee = Fixtures.createReferee(dealer);

            player.receiveInitialCards(List.of(new Card(CLOVER, TEN), new Card(SPADE, ACE)));
            dealer.receiveInitialCards(List.of(new Card(HEART, ACE), new Card(DIA, TEN)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(DRAW);
        }

        @Test
        @DisplayName("플레이어와 딜러의 결과, 카드 수가 모두 동일하면 무승부이다.")
        void sameScoreAndSameCardsCount() {
            Player player = Fixtures.createPlayer();
            Dealer dealer = Fixtures.createDealer();
            Referee referee = Fixtures.createReferee(dealer);

            player.receiveInitialCards(List.of(new Card(CLOVER, FIVE), new Card(SPADE, FIVE)));
            dealer.receiveInitialCards(List.of(new Card(HEART, FIVE), new Card(DIA, FIVE)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(DRAW);
        }
    }

    static class Fixtures {

        static Player createPlayer() {
            return new Player("몰리");
        }

        static Dealer createDealer() {
            return new Dealer(new Deck());
        }

        static Referee createReferee(Dealer dealer) {
            return new Referee(dealer);
        }
    }
}
