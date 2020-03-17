package domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Symbol;

class ParticipantTest {

    @Test
    @DisplayName("카드를 더하면 제대로 ParticipantCards에 더해지고, 점수 계산이 제대로 되는지")
    void calculateScore() {
        Participant participant = new Participant("참여자");
        participant.receive(new Card(Symbol.ACE, Shape.클로버));
        participant.receive(new Card(Symbol.TWO, Shape.다이아몬드));
        assertThat(participant.calculateScore()).isEqualTo(3);
    }

    @Test
    @DisplayName("버스트 확인이 제대로 되는지")
    void isBust() {
        Participant participant = new Participant("참여자");
        participant.receive(new Card(Symbol.TEN, Shape.클로버));
        participant.receive(new Card(Symbol.NINE, Shape.클로버));
        assertThat(participant.isBust()).isEqualTo(false);
        participant.receive(new Card(Symbol.EIGHT, Shape.클로버));
        assertThat(participant.isBust()).isEqualTo(true);
    }
}
