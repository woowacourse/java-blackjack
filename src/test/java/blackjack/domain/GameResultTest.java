package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTest {

    @ParameterizedTest(name = "플레이어의 점수가 {1} 이고 딜러의 점수가 {2} 이면, 플레이어의 결과는 {0} 이다.")
    @MethodSource("provideScoreAndResult")
    @DisplayName("플레이어와 딜러가 모두 bust 가 아닐 때, 플레이어의 결과를 계산한다.")
    void findPlayerResult(GameResult gameResult, int playerScore, int dealerScore) {
        final GameResult actual = GameResult.compareScore(playerScore, dealerScore);

        assertThat(actual).isEqualTo(gameResult);
    }

    static Stream<Arguments> provideScoreAndResult() {
        return Stream.of(
                Arguments.of(GameResult.DRAW, 21, 21),
                Arguments.of(GameResult.WIN, 21, 20),
                Arguments.of(GameResult.LOSE, 20, 21)
        );
    }

    @Test
    @DisplayName("플레이어가 bust 일 때, 딜러의 점수와 상관없이 플레이어의 결과는 LOSE 이다.")
    void findPlayerResultPlayerBust() {
        Dealer dealer = new Dealer(new Cards(Arrays.asList(
                new Card(CardPattern.HEART, CardNumber.TEN),
                new Card(CardPattern.HEART, CardNumber.NINE),
                new Card(CardPattern.HEART, CardNumber.THREE)
        )));
        Player player = new Player("slow", new Cards(Arrays.asList(
                new Card(CardPattern.HEART, CardNumber.TEN),
                new Card(CardPattern.HEART, CardNumber.NINE),
                new Card(CardPattern.HEART, CardNumber.THREE)
        )));
        final GameResult gameResult = GameResult.LOSE;

        final GameResult actual = GameResult.findPlayerResult(player, dealer);

        assertThat(actual).isEqualTo(gameResult);
    }

    @Test
    @DisplayName("딜러가 bust 일 때, 플레이어가 bust가 아니라면 플레이어의 결과는 WIN 이다.")
    void findPlayerResultDealerBust() {
        Dealer dealer = new Dealer(new Cards(Arrays.asList(
                new Card(CardPattern.HEART, CardNumber.TEN),
                new Card(CardPattern.HEART, CardNumber.NINE),
                new Card(CardPattern.HEART, CardNumber.THREE)
        )));
        Player player = new Player("slow", new Cards(Arrays.asList(
                new Card(CardPattern.HEART, CardNumber.THREE),
                new Card(CardPattern.HEART, CardNumber.TEN)
        )));
        final GameResult gameResult = GameResult.WIN;

        final GameResult actual = GameResult.findPlayerResult(player, dealer);

        assertThat(actual).isEqualTo(gameResult);
    }

    @ParameterizedTest(name = "플레이어의 결과가 {0} 이라면, 딜러의 결과는 {1} 이다.")
    @MethodSource("provideResultsOfPlayerAndDealer")
    @DisplayName("딜러의 결과를 계산한다.")
    void findDealerResult(GameResult playerResult, GameResult dealerResult) {
        final GameResult actual = GameResult.findDealerResult(playerResult);

        assertThat(actual).isEqualTo(dealerResult);
    }

    static Stream<Arguments> provideResultsOfPlayerAndDealer() {
        return Stream.of(
                Arguments.of(GameResult.WIN, GameResult.LOSE),
                Arguments.of(GameResult.LOSE, GameResult.WIN),
                Arguments.of(GameResult.DRAW, GameResult.DRAW)
        );
    }
}
