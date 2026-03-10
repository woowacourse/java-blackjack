package blackjack.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    @Test
    @DisplayName("카드가 2장이고, 점수가 21이면 블랙잭이다")
    void isBlackjackTest() {
        // given
        Participant participant = new Player("usher");
        participant.receiveCard(new Card(Figure.DIAMOND, Number.ACE));
        participant.receiveCard(new Card(Figure.DIAMOND, Number.KING));

        // when & then
        Assertions.assertThat(participant.isBlackjack()).isTrue();
    }
}
