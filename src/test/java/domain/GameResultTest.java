package domain;

import static domain.participant.GameResult.DRAW;
import static domain.participant.GameResult.LOSE;
import static domain.participant.GameResult.WIN;
import static domain.participant.GameResult.calculateResultOfPlayer;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import domain.participant.Betting;
import domain.participant.Dealer;
import domain.participant.GameResult;
import domain.participant.Participant;
import domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultTest {

    Participant player;
    Participant dealer;

    @BeforeEach
    void initParticipants() {
        player = new Player("pobi", new Betting(10000));
        dealer = new Dealer();
    }

    @Nested
    @DisplayName("버스트로 비기는 경우")
    class WhenBurstWinCase {

        @Test
        @DisplayName("딜러22, 플레이어 22로 둘 다 버스트 될 경우 DRAW가 반환되어야 한다")
        void should_return_draw_when_both_bust_and_player_is_same_dealer() {
            //given
            dealer.addCard(new Card(Shape.HEART, Rank.JACK));
            dealer.addCard(new Card(Shape.HEART, Rank.KING));
            dealer.addCard(new Card(Shape.HEART, Rank.TWO));
            player.addCard(new Card(Shape.CLUB, Rank.JACK));
            player.addCard(new Card(Shape.CLUB, Rank.KING));
            player.addCard(new Card(Shape.CLUB, Rank.TWO));

            // when
            GameResult playerResult = calculateResultOfPlayer(dealer, player);

            // then
            assertThat(playerResult).isEqualTo(DRAW);
        }

        @Test
        @DisplayName("딜러23, 플레이어 22로 딜러값이 플레이어보다 큰 경우에도 버스트 되었다면, 무승부가 반환되어야 한다")
        void should_return_draw_when_both_bust_and_dealer_is_over_than_player() {
            //given
            dealer.addCard(new Card(Shape.HEART, Rank.JACK));
            dealer.addCard(new Card(Shape.HEART, Rank.KING));
            dealer.addCard(new Card(Shape.HEART, Rank.THREE));
            player.addCard(new Card(Shape.CLUB, Rank.JACK));
            player.addCard(new Card(Shape.CLUB, Rank.KING));
            player.addCard(new Card(Shape.CLUB, Rank.TWO));

            // when
            GameResult playerResult = calculateResultOfPlayer(dealer, player);

            // then
            assertThat(playerResult).isEqualTo(DRAW);
        }

        @Test
        @DisplayName("딜러22, 플레이어 23로 플레이어값이 딜러보다 큰 경우에도 버스트 되었다면, 무승부가 반환되어야 한다")
        void should_return_draw_when_both_bust_and_player_is_over_than_dealer() {
            //given
            dealer.addCard(new Card(Shape.HEART, Rank.JACK));
            dealer.addCard(new Card(Shape.HEART, Rank.KING));
            dealer.addCard(new Card(Shape.HEART, Rank.TWO));
            player.addCard(new Card(Shape.CLUB, Rank.JACK));
            player.addCard(new Card(Shape.CLUB, Rank.KING));
            player.addCard(new Card(Shape.CLUB, Rank.THREE));

            // when
            GameResult playerResult = calculateResultOfPlayer(dealer, player);

            // then
            assertThat(playerResult).isEqualTo(DRAW);
        }
    }

    @Test
    @DisplayName("플레이어의 버스트로 버스트로 딜러가 승리하는 경우")
    void should_return_lose_when_player_burst() {
        //given
        dealer.addCard(new Card(Shape.HEART, Rank.A));
        player.addCard(new Card(Shape.HEART, Rank.JACK));
        player.addCard(new Card(Shape.HEART, Rank.QUEEN));
        player.addCard(new Card(Shape.HEART, Rank.TWO));

        // when
        GameResult playerResult = calculateResultOfPlayer(dealer, player);

        // then
        assertThat(playerResult).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("딜러의 버스트로 플레이어가 승리하는 경우 딜러가 패배하는 경우")
    void should_return_win_when_dealer_burst() {
        //given
        dealer.addCard(new Card(Shape.HEART, Rank.TWO));
        dealer.addCard(new Card(Shape.HEART, Rank.JACK));
        dealer.addCard(new Card(Shape.HEART, Rank.QUEEN));
        player.addCard(new Card(Shape.HEART, Rank.TWO));

        // when
        GameResult playerResult = calculateResultOfPlayer(dealer, player);

        // then
        assertThat(playerResult).isEqualTo(WIN);
    }

    @Test
    @DisplayName("점수가 동일하여 비기는 경우")
    void should_return_draw_when_same_value() {
        //given
        dealer.addCard(new Card(Shape.HEART, Rank.TWO));
        dealer.addCard(new Card(Shape.HEART, Rank.JACK));
        player.addCard(new Card(Shape.SPADE, Rank.TWO));
        player.addCard(new Card(Shape.SPADE, Rank.JACK));

        // when
        GameResult playerResult = calculateResultOfPlayer(dealer, player);

        // then
        assertThat(playerResult).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("딜러의 점수가 더 높아 지는 경우")
    void should_return_lose_when_dealer_value_high() {
        //given
        dealer.addCard(new Card(Shape.HEART, Rank.TWO));
        dealer.addCard(new Card(Shape.HEART, Rank.JACK));
        player.addCard(new Card(Shape.SPADE, Rank.TWO));

        // when
        GameResult playerResult = calculateResultOfPlayer(dealer, player);

        // then
        assertThat(playerResult).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("딜러의 점수가 더 낮아 이기는 경우")
    void should_return_win_when_player_value_high() {
        //given
        dealer.addCard(new Card(Shape.HEART, Rank.TWO));
        player.addCard(new Card(Shape.HEART, Rank.TWO));
        player.addCard(new Card(Shape.SPADE, Rank.JACK));

        // when
        GameResult playerResult = calculateResultOfPlayer(dealer, player);

        // then
        assertThat(playerResult).isEqualTo(WIN);
    }

    @ParameterizedTest
    @DisplayName("반대의 결과를 계산한다")
    @CsvSource(value = {"WIN, LOSE", "DRAW, DRAW", "LOSE, WIN", "BLACKJACK, LOSE"})
    void should_return_reverse_result(GameResult given, GameResult expected) {
        // when
        GameResult result = given.convertOfDealer();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("버스트 값이면 true를 반환한다")
    @CsvSource(value = {"21, false", "22, true"})
    void should_return_true_when_burst(int value, boolean expected) {
        // when
        boolean result = dealer.isBust(value);

        // then
        assertThat(result).isEqualTo(expected);
    }
}


