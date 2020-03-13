package blackjack.card.domain;

import blackjack.card.domain.component.CardNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.card.domain.CardBundleHelper.aCardBundle;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameResultTest {

    private static Stream<Arguments> cardBundleProvider() {
        return Stream.of(
                Arguments.of(aCardBundle(CardNumber.FIVE), aCardBundle(CardNumber.TWO), GameResult.WIN),
                Arguments.of(aCardBundle(CardNumber.ACE), aCardBundle(CardNumber.ACE), GameResult.DRAW),
                Arguments.of(aCardBundle(CardNumber.TWO), aCardBundle(CardNumber.FIVE), GameResult.LOSE)
        );
    }

    private static Stream<Arguments> nullCardBundleProvider() {
        return Stream.of(
                Arguments.of(null, aCardBundle(CardNumber.TWO), "갬블러의 카드가 없습니다."),
                Arguments.of(aCardBundle(CardNumber.ACE), null, "딜러의 카드가 없습니다.")
        );
    }

    @DisplayName("GameResult에서 값(승,무,패) 찾기")
    @ParameterizedTest
    @MethodSource("cardBundleProvider")
    void findByResult(CardBundle gamblerCardBundle, CardBundle dealerCardBundle, GameResult expect) {
        GameResult actual = GameResult.findByComparing(gamblerCardBundle, dealerCardBundle);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("GameResult에서 값(승,무,패) 찾기")
    @ParameterizedTest
    @MethodSource("nullCardBundleProvider")
    void findByResultException(CardBundle gamblerCardBundle, CardBundle dealerCardBundle, String message) {
        assertThatThrownBy(() -> GameResult.findByComparing(gamblerCardBundle, dealerCardBundle))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

}