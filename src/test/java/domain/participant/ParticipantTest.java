package domain.participant;

import static domain.card.Number.TWO;
import static domain.card.Shape.DIAMOND;
import static org.junit.jupiter.api.Assertions.*;

import domain.card.Card;
import domain.card.Hand;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("참가자 카드 추가 테스트")
    void addCardTest() {
        //given
        Participant participant = new Participant(new Hand(new ArrayList<>()));
        Card card = new Card(DIAMOND, TWO);

        //when
        participant.addCard(card);

        //given
        assertEquals(participant.getHand().getHand().getFirst(), card);
    }

}
