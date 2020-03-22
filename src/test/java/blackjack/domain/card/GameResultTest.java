package blackjack.domain.card;

import blackjack.domain.card.component.CardNumber;
import blackjack.domain.result.GameResult;
import blackjack.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.domain.card.CardBundleHelper.aCardBundle;
import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    private static Stream<Arguments> cardBundleProvider() {
        return Stream.of(
                Arguments.of(aCardBundle(CardNumber.FIVE), aCardBundle(CardNumber.TWO), GameResult.WIN),
                Arguments.of(aCardBundle(CardNumber.ACE), aCardBundle(CardNumber.ACE), GameResult.DRAW),
                Arguments.of(aCardBundle(CardNumber.TWO), aCardBundle(CardNumber.FIVE), GameResult.LOSE)
        );
    }

    @DisplayName("GameResult에서 값(승,무,패) 찾기")
    @ParameterizedTest
    @MethodSource("cardBundleProvider")
    void findByResult(CardBundle gamblerCardBundle, CardBundle dealerCardBundle, GameResult expect) {
        Score gamblerScore = new Score(gamblerCardBundle);
        Score dealerScore = new Score(dealerCardBundle);
        GameResult actual = GameResult.findByScores(dealerScore, gamblerScore);

        assertThat(actual).isEqualTo(expect);
    }

}