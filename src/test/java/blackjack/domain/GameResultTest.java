package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("게임 결과 반환 테스트")
class GameResultTest {

    private static final Card ACE = new Card(CardNumber.ACE, CardShape.CLOVER);
    private static final Card FIVE = new Card(CardNumber.FIVE, CardShape.CLOVER);
    private static final Card TEN = new Card(CardNumber.TEN, CardShape.CLOVER);

    private static final int BAT_AMOUNT = 1_000;

    @ParameterizedTest
    @MethodSource("finalResultExpectedData")
    @DisplayName("최종 수익을 종합해 반환한다")
    void calculateFinalEarnings(Cards dealerCard, Cards playerCard, double dealerExcepted, double playerExcepted) {
        Dealer dealer = new Dealer();
        dealer.addCards(dealerCard);

        Gambler gambler = new Gambler("비타", BAT_AMOUNT);
        gambler.addCards(playerCard);

        GameResult gameResults = new GameResult();
        gameResults.processResult(dealer, gambler);
        Map<Player, Integer> result = gameResults.getGameResults();

        assertAll(
                () -> assertThat(result.get(dealer)).isEqualTo((int) dealerExcepted),
                () -> assertThat(result.get(gambler)).isEqualTo((int) playerExcepted)
        );
    }

    @ParameterizedTest
    @MethodSource("finalResultWithBlackJackExpectedData")
    @DisplayName("블랙잭 상황에서 참가자의 수익은 1.5배이다")
    void earningsAre1Point5TimesInBlackjack(Cards dealerCard, Cards playerCard, double dealerExcepted, double playerExcepted) {
        Dealer dealer = new Dealer();
        dealer.addCards(dealerCard);

        Gambler gambler = new Gambler("비타", BAT_AMOUNT);
        gambler.addCards(playerCard);

        GameResult gameResults = new GameResult();
        gameResults.processResult(dealer, gambler);
        Map<Player, Integer> result = gameResults.getGameResults();

        assertAll(
                () -> assertThat(result.get(dealer)).isEqualTo((int) dealerExcepted),
                () -> assertThat(result.get(gambler)).isEqualTo((int) playerExcepted)
        );
    }

    private static Stream<Arguments> finalResultExpectedData() {
        return Stream.of(
                Arguments.of(new Cards(List.of(ACE)), new Cards(List.of(ACE, ACE)), -1_000, 1_000),
                Arguments.of(new Cards(List.of(ACE, ACE)), new Cards(List.of(ACE)), 1_000, -1_000),
                Arguments.of(new Cards(List.of(ACE)), new Cards(List.of(ACE)), 0, 0)
        );
    }

    private static Stream<Arguments> finalResultWithBlackJackExpectedData() {
        return Stream.of(
                Arguments.of(new Cards(List.of(ACE, TEN)), new Cards(List.of(ACE, ACE)), BAT_AMOUNT * 1.0, BAT_AMOUNT * -1.0),
                Arguments.of(new Cards(List.of(ACE, TEN)), new Cards(List.of(ACE, FIVE, FIVE)), BAT_AMOUNT * 1.0, BAT_AMOUNT * -1.0),
                Arguments.of(new Cards(List.of(ACE, TEN)), new Cards(List.of(ACE, TEN)), 0, 0),
                Arguments.of(new Cards(List.of(ACE)), new Cards(List.of(ACE, TEN)), BAT_AMOUNT * -1.5, BAT_AMOUNT * 1.5),
                Arguments.of(new Cards(List.of(ACE, FIVE, FIVE)), new Cards(List.of(ACE, TEN)), BAT_AMOUNT * -1.5, BAT_AMOUNT * 1.5)
        );
    }
}
