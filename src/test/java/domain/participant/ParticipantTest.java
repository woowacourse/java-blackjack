package domain.participant;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParticipantTest {

    @DisplayName("사용자의 점수가 21이하면 카드를 받을 수 없다.")
    @Test
    void canHit() {
        Participant participant = new Participant(new Name("user"));
        participant.receiveCard(new Card(Shape.HEART, Rank.KING));
        participant.receiveCard(new Card(Shape.DIA, Rank.FIVE));
        participant.receiveCard(new Card(Shape.HEART, Rank.SIX));

        boolean canHit = participant.canHit();

        assertThat(canHit).isTrue();
    }

    @DisplayName("사용자의 점수가 22이상이면 카드를 받을 수 없다.")
    @Test
    void canNotHit() {
        Participant participant = new Participant(new Name("user"));
        participant.receiveCard(new Card(Shape.HEART, Rank.KING));
        participant.receiveCard(new Card(Shape.DIA, Rank.KING));
        participant.receiveCard(new Card(Shape.HEART, Rank.TWO));

        boolean canHit = participant.canHit();

        assertThat(canHit).isFalse();
    }
}
