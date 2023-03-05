package blackjack.domain.card;

import static blackjack.domain.card.Rank.ACE;
import static blackjack.domain.card.Rank.FOUR;
import static blackjack.domain.card.Rank.JACK;
import static blackjack.domain.card.Rank.KING;
import static blackjack.domain.card.Rank.SIX;
import static blackjack.domain.card.Shape.SPADE;
import static blackjack.domain.card.State.BLACKJACK;
import static blackjack.domain.card.State.BUST;
import static blackjack.domain.card.State.PLAY;
import static blackjack.domain.card.State.STOP;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

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
    void Cards를_받아_상태를_반환한다(final List<Rank> ranks, final State state) {
        final Cards cards = new Cards();
        for (Rank rank : ranks) {
            cards.add(new Card(rank, SPADE));
        }

        assertThat(State.calculateState(cards)).isEqualTo(state);
    }

    static Stream<Arguments> calculateStateSource() {
        return Stream.of(
                Arguments.of(List.of(ACE, JACK), BLACKJACK),
                Arguments.of(List.of(JACK, JACK, JACK), BUST),
                Arguments.of(List.of(ACE, FOUR, SIX), STOP),
                Arguments.of(List.of(KING, KING), PLAY)
        );
    }
}
