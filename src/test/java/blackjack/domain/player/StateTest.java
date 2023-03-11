package blackjack.domain.player;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.player.State.*;
import static blackjack.util.CardFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class StateTest {

    @ParameterizedTest(name = "블랙잭인지 확인한다. 입력값: {0}, 결과: {1}")
    @CsvSource({"BLACKJACK, true", "PLAY, false"})
    void 블랙잭인지_확인한다(final State state, final boolean result) {
        assertThat(state.isBlackjack()).isEqualTo(result);
    }

    @ParameterizedTest(name = "버스트인지 확인한다. 입력값: {0}, 결과: {1}")
    @CsvSource({"BUST, true", "PLAY, false"})
    void 버스트인지_확인한다(final State state, final boolean result) {
        assertThat(state.isBust()).isEqualTo(result);
    }

    @ParameterizedTest(name = "버스트가 아닌지 확인한다. 입력값: {0}, 결과: {1}")
    @CsvSource({"BUST, false", "PLAY, true"})
    void 버스트가_아닌지_확인한다(final State state, final boolean result) {
        assertThat(state.isNotBust()).isEqualTo(result);
    }

    @ParameterizedTest(name = "카드를 더 받을 수 있는지 확인한다. 입력값: {0}, 결과: {1}")
    @CsvSource({"BLACKJACK, false", "STOP, false", "PLAY, true", "BUST, false"})
    void 카드를_더_받을_수_있는지_확인한다(final State state, final boolean result) {
        assertThat(state.isPlayable()).isEqualTo(result);
    }

    @ParameterizedTest
    @MethodSource("calculateStateSource")
    void Cards를_받아_상태를_반환한다(final List<Card> testCards, final State state) {
        final Cards cards = new Cards();

        for (Card card : testCards) {
            cards.add(card);
        }

        assertThat(calculateState(cards)).isEqualTo(state);
    }

    static Stream<Arguments> calculateStateSource() {
        return Stream.of(
                Arguments.of(List.of(ACE_DIAMOND, JACK_SPADE), BLACKJACK),
                Arguments.of(List.of(JACK_SPADE, KING_SPADE, QUEEN_SPADE), BUST),
                Arguments.of(List.of(ACE_DIAMOND, FOUR_SPADE, SIX_SPADE), STOP),
                Arguments.of(List.of(KING_SPADE, QUEEN_SPADE), PLAY)
        );
    }
}
