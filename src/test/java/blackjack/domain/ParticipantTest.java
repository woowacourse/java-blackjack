package blackjack.domain;

import blackjack.fixture.ParticipantFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ParticipantTest {
    @Test
    @DisplayName("자신의 점수를 계산한다.")
    void getTotalPoint() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.SIX);
        Card cardTwo = new Card(CardShape.HEART, CardNumber.ACE);
        Participant participant = ParticipantFixture.create(List.of(cardOne, cardTwo));
        int totalPoint = participant.getTotalPoint();

        assertThat(totalPoint).isEqualTo(17);
    }
}