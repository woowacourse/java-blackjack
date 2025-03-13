package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
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

class GameResultTest {

    private static final Card ACE = new Card(CardNumber.ACE, CardShape.CLOVER);
    private static final Card FIVE = new Card(CardNumber.FIVE, CardShape.CLOVER);
    private static final Card TEN = new Card(CardNumber.TEN, CardShape.CLOVER);

    private static final int BAT_AMOUNT = 1_000;

    @ParameterizedTest
    @MethodSource("finalResultExpectedData")
    @DisplayName("최종 수익을 종합해 반환한다")
    void 최종_수익을_종합해_반환한다(List<Card> dealerCard, List<Card> playerCard, double dealerExcepted, double playerExcepted) {
        Dealer dealer = new Dealer();
        dealer.addCards(dealerCard);

        Gambler gambler = new Gambler("비타", BAT_AMOUNT);
        gambler.addCards(playerCard);

        Map<Player, Integer> result = new GameResults(dealer, List.of(gambler)).getGameResults();

        assertAll(
                () -> assertThat(result.get(dealer)).isEqualTo((int) dealerExcepted),
                () -> assertThat(result.get(gambler)).isEqualTo((int) playerExcepted)
        );
    }

    @ParameterizedTest
    @MethodSource("finalResultWithBlackJackExpectedData")
    @DisplayName("최종 수익을 블랙잭 상황을 고려해 반환한다")
    void 참가자가_블랙잭인_경우_수익은_1_5배이다(List<Card> dealerCard, List<Card> playerCard, double dealerExcepted, double playerExcepted) {
        Dealer dealer = new Dealer();
        dealer.addCards(dealerCard);

        Gambler gambler = new Gambler("비타", BAT_AMOUNT);
        gambler.addCards(playerCard);

        GameResults gameResults = new GameResults(dealer, List.of(gambler));

        Map<Player, Integer> result = gameResults.getGameResults();

        assertAll(
                () -> assertThat(result.get(dealer)).isEqualTo((int) dealerExcepted),
                () -> assertThat(result.get(gambler)).isEqualTo((int) playerExcepted)
        );
    }

    private static Stream<Arguments> finalResultExpectedData() {
        return Stream.of(
                Arguments.of(List.of(ACE), List.of(ACE, ACE), -1_000, 1_000),
                Arguments.of(List.of(ACE, ACE), List.of(ACE), 1_000, -1_000),
                Arguments.of(List.of(ACE), List.of(ACE), 0, 0)
        );
    }

    private static Stream<Arguments> finalResultWithBlackJackExpectedData() {
        return Stream.of(
                Arguments.of(List.of(ACE, TEN), List.of(ACE, ACE), BAT_AMOUNT * 1.0, BAT_AMOUNT * -1.0),
                Arguments.of(List.of(ACE, TEN), List.of(ACE, FIVE, FIVE), BAT_AMOUNT * 1.0, BAT_AMOUNT * -1.0),
                Arguments.of(List.of(ACE, TEN), List.of(ACE, TEN), 0, 0),
                Arguments.of(List.of(ACE), List.of(ACE, TEN), BAT_AMOUNT * -1.5, BAT_AMOUNT * 1.5),
                Arguments.of(List.of(ACE, FIVE, FIVE), List.of(ACE, TEN), BAT_AMOUNT * -1.5, BAT_AMOUNT * 1.5)
        );
    }
}
