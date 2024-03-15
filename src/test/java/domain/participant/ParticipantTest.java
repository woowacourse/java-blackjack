package domain.participant;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Denomination;
import domain.card.Score;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    @Test
    void 초기_카드를_2장_받는다() {
        Participant participant = new Player("prin");

        participant.receiveInitialCards(카드(), 카드());

        assertThat(participant.getAllCards()).hasSize(2);
    }

    @Test
    void 추가_카드를_받는다() {
        Participant player = new Player("prin");

        player.receiveAdditionalCard(카드());

        assertThat(player.getAllCards()).hasSize(1);
    }

    @Test
    void 카드의_합을_계산한다() {
        Participant participant = new Player("prin");

        participant.receiveInitialCards(카드(Denomination.KING), 카드(Denomination.SIX));

        Score result = participant.calculateScore();
        assertThat(result).isEqualTo(Score.get(16));
    }
}
