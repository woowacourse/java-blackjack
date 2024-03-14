package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @DisplayName("사용자의 점수가 21이하인지 확인한다.")
    @Test
    void canHit() {
        Participant participant = new Participant(new Name("user"));
        participant.receiveCard(new Card(Shape.HEARTS, Rank.KING));
        participant.receiveCard(new Card(Shape.DIAMONDS, Rank.KING));
        participant.receiveCard(new Card(Shape.HEARTS, Rank.TWO));

        boolean canHit = participant.canHit();

        assertThat(canHit).isFalse();
    }

    @DisplayName("사용자가 블랙잭인지 확인한다.")
    @Test
    void isBlackJack() {
        Participant participant = new Participant(new Name("user"));
        participant.receiveCard(new Card(Shape.HEARTS, Rank.KING));
        participant.receiveCard(new Card(Shape.DIAMONDS, Rank.ACE));

        boolean canHit = participant.isBlackJack();

        assertThat(canHit).isTrue();
    }
}
