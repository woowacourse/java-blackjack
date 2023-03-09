package blackjack.domain.card;

import static blackjack.domain.card.State.BLACKJACK;
import static blackjack.domain.card.State.BUST;
import static blackjack.domain.card.State.PLAY;
import static blackjack.domain.card.State.STOP;
import static blackjack.util.CardFixtures.ACE_SPADE;
import static blackjack.util.CardFixtures.FOUR_SPADE;
import static blackjack.util.CardFixtures.JACK_CLOVER;
import static blackjack.util.CardFixtures.JACK_HEART;
import static blackjack.util.CardFixtures.JACK_SPADE;
import static blackjack.util.CardFixtures.KING_HEART;
import static blackjack.util.CardFixtures.KING_SPADE;
import static blackjack.util.CardFixtures.SIX_SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class StateTest {

    @ParameterizedTest
    @MethodSource("calculateStateSource")
    void Cards를_받아_상태를_반환한다(final List<Card> cards, final State state) {
        final Cards sut = new Cards(cards);

        assertThat(State.calculateState(sut)).isEqualTo(state);
    }

    static Stream<Arguments> calculateStateSource() {
        return Stream.of(
                Arguments.of(List.of(ACE_SPADE, JACK_SPADE), BLACKJACK),
                Arguments.of(List.of(JACK_SPADE, JACK_HEART, JACK_CLOVER), BUST),
                Arguments.of(List.of(ACE_SPADE, FOUR_SPADE, SIX_SPADE), STOP),
                Arguments.of(List.of(KING_SPADE, KING_HEART), PLAY)
        );
    }
}
