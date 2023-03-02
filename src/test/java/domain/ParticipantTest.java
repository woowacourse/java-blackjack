package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("딜러는 참가자에게 카드를 분배한다.")
    void dealCardToPlayerTest() {
        Participant participant = new Participant("echo");
        participant.addCard(new Card(CardNumber.ACE, CardShape.SPADE));
        Assertions.assertThat(participant)
            .extracting("cards")
            .asList()
            .hasSize(1);
        Assertions.assertThat(participant)
            .extracting("cards")
            .asList()
            .first()
            .isEqualTo(new Card(CardNumber.ACE, CardShape.SPADE));
    }
}
