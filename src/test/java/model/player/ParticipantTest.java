package model.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @DisplayName("참가자의 수가 1명 미만이면 예외가 발생한다.")
    @Test
    void validateUnderOneParticipant() {
        assertThatThrownBy(() -> new Participants(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드의 합이 21이하일 때는 참을 반환한다.")
    @Test
    void noticeTrue() {
        Participant participant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.FIVE)));
        assertTrue(participant.canReceiveCard());
    }

    @DisplayName("카드의 합이 21초과일 때는 거짓을 반환한다.")
    @Test
    void noticeFalse() {
        Participant participant = new Participant("배키",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.FIVE)));
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        assertFalse(participant.canReceiveCard());
    }
}
