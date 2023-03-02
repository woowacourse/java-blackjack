package domain.area;

import domain.card.Card;
import domain.card.CardShape;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static domain.card.CardValue.SEVEN;
import static domain.card.CardValue.TEN;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("ParticipantCardArea 은")
class ParticipantCardAreaTest {

    @Test
    void 참가자가_카드를_더_받기_원할_때만_카드를_받는다() {
        // given
        final CardArea cardArea = new ParticipantCardArea(
                new Card(CardShape.CLOVER, TEN),
                new Card(CardShape.CLOVER, SEVEN)
        );

        final Participant participant = new Participant(new Name("player1"), cardArea);


        participant.changeState(State.HIT);

        // when & then
        assertTrue(cardArea.wantHit());
    }

    @Test
    void 참가자가_카드를_더_받기_원하지_않을_때_카드를_받지_않는다() {
        // given
        final CardArea cardArea = new ParticipantCardArea(
                new Card(CardShape.CLOVER, TEN),
                new Card(CardShape.CLOVER, SEVEN)
                );

        final Participant participant = new Participant(new Name("player1"), cardArea);


        participant.changeState(State.STAY);

        // when & then
        assertFalse(cardArea.wantHit());
    }
}