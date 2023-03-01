package participant;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Participant 은")
class ParticipantTest {

    @Test
    void 참가자는_상태를_바꿀_수_있다() {
        // given
        final Participant participant = new Participant();

        // when
        assertDoesNotThrow(() -> participant.changeState(State.HIT));
    }

    @Test
    void 참가자는_카드_받을_상태를_결정할_수_있다() {
        // given
        final Participant participant = new Participant();
        participant.changeState(State.HIT);

        // when & then
        assertTrue(participant.wantHit());
    }

    @Test
    void 참가자는_카드_받지_않을_상태를_결정할_수_있다() {
        // given
        final Participant participant = new Participant();
        participant.changeState(State.STAY);

        // when & then
        assertFalse(participant.wantHit());
    }
}