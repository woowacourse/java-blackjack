package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.player.Participant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @DisplayName("카드의 합이 21이하일 때는 참을 반환한다.")
    @Test
    void noticeTrue() {
        Participant participant = new Participant("배키");
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        assertTrue(participant.notice());
    }

    @DisplayName("카드의 합이 21초과일 때는 거짓을 반환한다.")
    @Test
    void noticeFalse() {
        Participant participant = new Participant("배키");
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        participant.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        assertFalse(participant.notice());
    }

    @DisplayName("딜러가 아니면 거짓을 반환한다.")
    @Test
    void isDealer() {
        Participant participant = new Participant("배키");
        assertFalse(participant.isDealer());
    }
}
