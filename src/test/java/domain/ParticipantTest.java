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

    @Test
    @DisplayName("처음에 카드를 지급받지 않은 경우 카드 조회시 오류를 던진다.")
    void getReadyCardsTestFailed() {
        Participant participant = new Participant("echo");
        Assertions.assertThatThrownBy(participant::getReadyCards)
            .isExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("처음에 지급받은 카드를 반환한다.")
    void getReadyCardsTestSuccess() {
        Participant participant = new Participant("echo");
        participant.addCard(new Card(CardNumber.ACE, CardShape.SPADE));
        participant.addCard(new Card(CardNumber.THREE, CardShape.HEART));
        Assertions.assertThat(participant.getReadyCards())
            .containsExactly(new Card(CardNumber.ACE, CardShape.SPADE), new Card(CardNumber.THREE, CardShape.HEART));
    }
}
