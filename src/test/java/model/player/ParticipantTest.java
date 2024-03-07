package model.player;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @DisplayName("카드의 합이 21이하일 때는 참을 반환한다.")
    @Test
    void noticeTrue() {
        Participant participant = new Participant("배키");
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        assertTrue(participant.receiveCard());
    }

    @DisplayName("카드의 합이 21초과일 때는 거짓을 반환한다.")
    @Test
    void noticeFalse() {
        Participant participant = new Participant("배키");
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        assertFalse(participant.receiveCard());
    }
}
