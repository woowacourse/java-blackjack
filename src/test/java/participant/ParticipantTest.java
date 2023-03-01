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
        final Participant participant = new Participant(new Name("이름"));

        // when
        assertDoesNotThrow(() -> participant.changeState(State.HIT));
    }

    @Test
    void 참가자는_카드_받을_상태를_결정할_수_있다() {
        // given
        final Participant participant = new Participant(new Name("이름"));
        participant.changeState(State.HIT);

        // when & then
        assertTrue(participant.wantHit());
    }

    @Test
    void 참가자는_카드_받지_않을_상태를_결정할_수_있다() {
        // given
        final Participant participant = new Participant(new Name("이름"));
        participant.changeState(State.STAY);

        // when & then
        assertFalse(participant.wantHit());
    }

    @Test
    void 참가자는_이름을_가질_수_있다() {
        // given
        final String name = "이름";
        final Participant participant = new Participant(new Name(name));

        // when & then
        Assertions.assertEquals(participant.name().value(), name);
    }
}