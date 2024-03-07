import domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParticipantTest {

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
}
