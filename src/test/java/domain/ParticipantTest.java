package domain;

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

public class ParticipantTest {

    Participant player;
    Participant dealer;

    @BeforeEach
    void initPlayer() {
        player = new Player("pobi", new Betting(10000));
        dealer = new Dealer();
    }

    @Nested
    @DisplayName("플레이어의 수익률을 계산한다")
    class CalculateProfitOfPlayer {

        @Test
        @DisplayName("플레이어가 10_000을 배팅하고 이기는 경우, 수익률은 10_000이어야 한다.")
        void calculate_player_profit_when_player_win() {
            //given
            int expectedValue = 10_000;
            player.addCard(new Card(Shape.HEART, Rank.TEN));
            dealer.addCard(new Card(Shape.HEART, Rank.EIGHT));

            //when, then
            assertPlayerProfit(player.getBettingAmount(), expectedValue);
        }

        @Test
        @DisplayName("플레이어가 10_000을 배팅하고 지는 경우, 수익률은 -10_000이어야 한다.")
        void calculate_player_profit_when_player_lose() {
            //given
            int expectedValue = -10_000;
            player.addCard(new Card(Shape.HEART, Rank.EIGHT));
            dealer.addCard(new Card(Shape.HEART, Rank.TEN));

            //when, then
            assertPlayerProfit(player.getBettingAmount(), expectedValue);
        }

        @Test
        @DisplayName("플레이어가 10_000을 배팅하고 비기는 경우, 수익률은 0이어야 한다.")
        void calculate_player_profit_when_player_draw() {
            //given
            int expectedValue = 0;
            player.addCard(new Card(Shape.HEART, Rank.EIGHT));
            dealer.addCard(new Card(Shape.SPADE, Rank.EIGHT));

            //when, then
            assertPlayerProfit(player.getBettingAmount(), expectedValue);
        }

        @Test
        @DisplayName("플레이어가 10_000을 배팅하고 블랙잭인 경우, 수익률은 15_000이어야 한다.")
        void calculate_player_profit_when_player_black_jack() {
            //given
            int expectedValue = 15_000;
            player.addCard(new Card(Shape.HEART, Rank.A));
            player.addCard(new Card(Shape.HEART, Rank.KING));
            dealer.addCard(new Card(Shape.HEART, Rank.TEN));

            //when, then
            assertPlayerProfit(player.getBettingAmount(), expectedValue);
        }

        private void assertPlayerProfit(int bettingAmount, int expectedValue) {
            GameResult gameResult = GameResult.calculateResultOfPlayer(dealer, player);
            double calculateValue = gameResult.getCalculateValue(bettingAmount);
            assertThat(calculateValue).isEqualTo(expectedValue);
        }
    }

    @Test
    @DisplayName("KING, A로 인해 blackJack인 경우 true를 반환해야 한다")
    void when_black_jack_return_true() {
        //given
        player.addCard(new Card(Shape.HEART, Rank.A));
        player.addCard(new Card(Shape.HEART, Rank.JACK));

        //when
        boolean blackJack = player.isBlackJack();

        //then
        assertThat(blackJack).isTrue();
    }

    @Test
    @DisplayName("KING, FIVE, SIX로 인해 합이 21인 경우, 첫 두 장이 아니기에 false를 반환한다")
    void when_not_enough_size_then_return_false() {
        //given
        player.addCard(new Card(Shape.HEART, Rank.KING));
        player.addCard(new Card(Shape.HEART, Rank.FIVE));
        player.addCard(new Card(Shape.HEART, Rank.SIX));

        //when
        boolean blackJack = player.isBlackJack();

        //then
        assertThat(blackJack).isFalse();
    }

    @Test
    @DisplayName("카드 수가 2장이지만, 합이 21이 아닐 경우 false를 반환한다.")
    void when_not_enough_sum_then_return_false() {
        //given
        player.addCard(new Card(Shape.HEART, Rank.KING));
        player.addCard(new Card(Shape.HEART, Rank.FIVE));

        //when
        boolean blackJack = player.isBlackJack();

        //then
        assertThat(blackJack).isFalse();
    }
}
