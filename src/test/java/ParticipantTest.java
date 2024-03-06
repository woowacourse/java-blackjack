import domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParticipantTest {
    @DisplayName("사용자의 점수를 계산한다.")
    @Test
    void calculateScore() {
        Participant participant = new Participant(new Name("user"));
        participant.receiveCard(new Card(Shape.HEART, Rank.KING));
        int score = participant.calculateScore();

        assertThat(score).isEqualTo(10);
    }

    @DisplayName("사용자의 점수가 21이하인지 확인한다.")
    @Test
    void canHit() {
        Participant participant = new Participant(new Name("user"));
        participant.receiveCard(new Card(Shape.HEART, Rank.KING));
        participant.receiveCard(new Card(Shape.DIA, Rank.KING));
        participant.receiveCard(new Card(Shape.HEART, Rank.TWO));

        boolean canHit = participant.canHit();

        assertThat(canHit).isFalse();

    }

    @Test
    @DisplayName("사용자의 카드중 ACE 포함여부를 반환한다. ")
    void hasAce() {
        Participant participant = new Participant(new Name("user"));
        participant.receiveCard(new Card(Shape.HEART, Rank.ACE));

        //when
        boolean hasAce = participant.hasAce();
        //then
        assertThat(hasAce).isTrue();
    }


    @Test
    @DisplayName("ace는 1 또는 11 중 하나로 결정")
    void calculateAceIsEleven() {
        Participant participant = new Participant(new Name("user"));
        participant.receiveCard(new Card(Shape.HEART, Rank.KING));
        participant.receiveCard(new Card(Shape.HEART, Rank.ACE));

        int totalScore = participant.calculateScore();

        Assertions.assertThat(totalScore).isEqualTo(21);
    }

    @Test
    @DisplayName("ace는 1 또는 11 중 하나로 결정")
    void calculateAceIsOne() {
        Participant participant = new Participant(new Name("user"));
        participant.receiveCard(new Card(Shape.HEART, Rank.KING));
        participant.receiveCard(new Card(Shape.HEART, Rank.NINE));
        participant.receiveCard(new Card(Shape.HEART, Rank.ACE));

        int totalScore = participant.calculateScore();

        Assertions.assertThat(totalScore).isEqualTo(20);
    }
}
