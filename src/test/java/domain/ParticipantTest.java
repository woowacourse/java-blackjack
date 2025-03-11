package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.GameResult;
import domain.participant.Participant;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ParticipantTest {

    @Nested
    @DisplayName("플레이어의 수익률을 계산한다")
    class CalculateProfit {

        @Test
        @DisplayName("플레이어가 10_000을 배팅하고 이기는 경우, 수익률은 10_000이어야 한다.")
        void calculate_player_profit_when_player_win() {
            Participant player = new Player("pobi");
            int bettingAmount = 10000;
            player.startBetting(bettingAmount);
            Participant dealer = new Dealer();

            player.addCard(new Card(Shape.HEART, Rank.TEN));
            dealer.addCard(new Card(Shape.HEART, Rank.EIGHT));
            GameResult gameResult = GameResult.calculateResultOfPlayer(dealer, player);

            int expectedValue = 10_000;
            double calculateValue = gameResult.getCalculateValue(bettingAmount);
            assertThat(calculateValue).isEqualTo(expectedValue);
        }

        @Test
        @DisplayName("플레이어가 10_000을 배팅하고 지는 경우, 수익률은 -10_000이어야 한다.")
        void calculate_player_profit_when_player_lose() {
            Participant player = new Player("pobi");
            int bettingAmount = 10000;
            player.startBetting(bettingAmount);
            Participant dealer = new Dealer();

            player.addCard(new Card(Shape.HEART, Rank.EIGHT));
            dealer.addCard(new Card(Shape.HEART, Rank.TEN));

            GameResult gameResult = GameResult.calculateResultOfPlayer(dealer, player);

            int expectedValue = -10_000;
            double calculateValue = gameResult.getCalculateValue(bettingAmount);
            assertThat(calculateValue).isEqualTo(expectedValue);
        }

        @Test
        @DisplayName("플레이어가 10_000을 배팅하고 비기는 경우, 수익률은 0이어야 한다.")
        void calculate_player_profit_when_player_draw() {
            Participant player = new Player("pobi");
            int bettingAmount = 10000;
            player.startBetting(bettingAmount);
            Participant dealer = new Dealer();

            player.addCard(new Card(Shape.HEART, Rank.EIGHT));
            dealer.addCard(new Card(Shape.SPADE, Rank.EIGHT));
            GameResult gameResult = GameResult.calculateResultOfPlayer(dealer, player);

            int expectedValue = 0;
            double calculateValue = gameResult.getCalculateValue(bettingAmount);
            assertThat(calculateValue).isEqualTo(expectedValue);
        }

        @Test
        @DisplayName("플레이어가 10_000을 배팅하고 블랙잭인 경우, 수익률은 15_000이어야 한다.")
        void calculate_player_profit_when_player_black_jack() {
            Participant player = new Player("pobi");
            int bettingAmount = 10000;
            player.startBetting(bettingAmount);
            Participant dealer = new Dealer();

            player.addCard(new Card(Shape.HEART, Rank.A));
            player.addCard(new Card(Shape.HEART, Rank.KING));
            dealer.addCard(new Card(Shape.HEART, Rank.TEN));
            GameResult gameResult = GameResult.calculateResultOfPlayer(dealer, player);

            System.out.println(gameResult.getKoreanName());
            int expectedValue = 15_000;
            double calculateValue = gameResult.getCalculateValue(bettingAmount);
            assertThat(calculateValue).isEqualTo(expectedValue);
        }
    }
}
