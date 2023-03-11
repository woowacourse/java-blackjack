package blackjack.domain.participant;

import static blackjack.domain.card.CardFixture.CLOVER_ACE;
import static blackjack.domain.card.CardFixture.SPADE_ACE;
import static blackjack.domain.card.CardResponseFixture.CLOVER_ACE_RESPONSE;
import static blackjack.domain.card.CardResponseFixture.SPADE_ACE_RESPONSE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class ParticipantTest {

    private Participant participant;

    @BeforeEach
    void setUp() {
        participant = new Participant() {
            @Override
            public boolean isDrawable() {
                return true;
            }
        };
        participant.drawInitialCard(SPADE_ACE, CLOVER_ACE);
    }

    @Test
    void 참가자는_점수를_계산할_수_있다() {

        final int score = participant.currentScore();

        assertThat(score).isEqualTo(12);
    }

    @Test
    void 참가자는_추가로_드로우를_해서_점수를_계산할_수_있다() {
        participant.drawCard(new Card(Shape.CLOVER, Symbol.ACE));

        final int score = participant.currentScore();

        assertThat(score).isEqualTo(13);
    }

    @Test
    void 참가자는_자기가_가지고_있는_카드를_확인할_수_있다() {
        assertThat(participant.getCards())
                .containsExactly(SPADE_ACE_RESPONSE, CLOVER_ACE_RESPONSE);
    }
}
