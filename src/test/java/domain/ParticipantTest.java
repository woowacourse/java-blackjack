package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @DisplayName("참가자는 카드의 합을 구할 수 있다.")
    @Test
    void sumCards() {
        Participant participant = new Participant();
        participant.receive(new Card(CardShape.SPADE, CardNumber.JACK));
        assertThat(participant.sumCards())
                .isEqualTo(10);
    }
}
