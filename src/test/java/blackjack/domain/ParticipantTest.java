package blackjack.domain;

import blackjack.fixture.ParticipantFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {
    @Test
    @DisplayName("자신의 점수를 계산한다.")
    void getTotalPoint() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.SIX);
        Card cardTwo = new Card(CardShape.HEART, CardNumber.ACE);
        Participant participant = ParticipantFixture.create(cardOne, cardTwo, List.of());
        int totalPoint = participant.getTotalPoint();

        assertThat(totalPoint).isEqualTo(17);
    }

    @Test
    @DisplayName("참가자가 카드를 뽑는다.")
    void hit() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(CardShape.DIAMOND, CardNumber.TWO);
        Participant participant = ParticipantFixture.create(cardOne, cardTwo, List.of());
        int beforeHitPoint = participant.getTotalPoint();
        participant.hit(new Card(CardShape.SPADE, CardNumber.ACE));
        int afterHitPoint = participant.getTotalPoint();

        assertThat(afterHitPoint).isGreaterThan(beforeHitPoint);
    }

    @Test
    @DisplayName("참가자가 카드를 보여준다.")
    void open() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(CardShape.DIAMOND, CardNumber.TWO);
        Participant participant = ParticipantFixture.create(cardOne, cardTwo, List.of());

        assertThat(participant.open(2)).containsAll(List.of(cardOne, cardTwo));
    }

    @Test
    @DisplayName("참가자의 결과를 가져온다.")
    void compare() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(CardShape.DIAMOND, CardNumber.TWO);
        Participant firstParticipant = ParticipantFixture.create(cardOne, cardTwo, List.of());
        Card secondCardOne = new Card(CardShape.DIAMOND, CardNumber.SIX);
        Card secondCardTwo = new Card(CardShape.HEART, CardNumber.ACE);
        Participant secondParticipant = ParticipantFixture.create(secondCardOne, secondCardTwo, List.of());

        assertThat(firstParticipant.compare(secondParticipant)).isEqualTo(ResultType.LOSE);
    }
}