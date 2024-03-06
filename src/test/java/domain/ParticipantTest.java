package domain;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    void 전달받은_카드를_패에_추가한다() {
        Participant participant = new Participant("pobi");

        participant.add(카드());

        assertThat(participant).extracting("cards")
                .extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(1);
    }

    @Test
    void 카드의_합을_계산한다() {
        Participant participant = new Participant("pobi");

        participant.add(카드(Denomination.KING));
        participant.add(카드(Denomination.SIX));

        int result = participant.calculateScore();

        assertThat(result).isEqualTo(16);
    }
}
