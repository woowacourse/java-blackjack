package domain.participant;

import domain.area.CardArea;
import domain.card.Card;
import domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static domain.card.CardValue.*;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Participant 은")
class ParticipantTest {

    final CardArea cardArea = new CardArea(
            new Card(CardShape.CLOVER, TEN),
            new Card(CardShape.CLOVER, SEVEN)
    );

    @Test
    void 참가자는_상태를_바꿀_수_있다() {
        // given
        final Participant participant = new Participant(new Name("player1"), cardArea);

        // when
        assertDoesNotThrow(() -> participant.changeState(State.HIT));
    }

    @ParameterizedTest
    @ValueSource(strings = "HIT")
    @NullSource
    void 참가자는_버스트되지_않았으면서_STAY_를_원하지_않을_때_카드를_더_받을_수_있다(final State state) {
        // given
        final Participant participant = new Participant(new Name("player1"), cardArea);

        participant.changeState(state);

        // when & then
        assertTrue(participant.canHit());
    }

    @ParameterizedTest
    @MethodSource("canNotMoreCard")
    void 참가자는_버스트되었거나_STAY_를_원한다면_카드를_더_받을_수_없다(final CardArea cardArea, final State state) {
        // given
        final Participant participant = new Participant(new Name("player1"), cardArea);
        participant.changeState(state);

        // when & then
        assertFalse(participant.canHit());
    }

    static Stream<Arguments> canNotMoreCard() {

        final CardArea under21CardArea = new CardArea(
                new Card(CardShape.SPADE, TEN),
                new Card(CardShape.DIAMOND, TEN)
        );

        final CardArea over21CardArea = new CardArea(
                new Card(CardShape.SPADE, TEN),
                new Card(CardShape.DIAMOND, TEN)
        );

        over21CardArea.addCard(new Card(CardShape.SPADE, TWO));

        return Stream.of(
                Arguments.of(under21CardArea, State.STAY),
                Arguments.of(over21CardArea, null),
                Arguments.of(over21CardArea, State.STAY),
                Arguments.of(over21CardArea, State.HIT)
        );
    }
}