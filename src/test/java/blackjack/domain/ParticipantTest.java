package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;
import blackjack.fixture.ParticipantFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {
    @Test
    @DisplayName("자신의 점수를 계산한다.")
    void getTotalPoint() {
        Card cardOne = new Card(Suit.DIAMOND, CardNumber.SIX);
        Card cardTwo = new Card(Suit.HEART, CardNumber.ACE);
        Participant participant = ParticipantFixture.create(cardOne, cardTwo, List.of());
        int totalPoint = participant.getTotalPoint();

        assertThat(totalPoint).isEqualTo(17);
    }

    @Test
    @DisplayName("참가자가 카드를 뽑는다.")
    void hit() {
        Card cardOne = new Card(Suit.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(Suit.DIAMOND, CardNumber.TWO);
        Participant participant = ParticipantFixture.create(cardOne, cardTwo, List.of());
        int beforeHitPoint = participant.getTotalPoint();
        participant.hit(new Card(Suit.SPADE, CardNumber.ACE));
        int afterHitPoint = participant.getTotalPoint();

        assertThat(afterHitPoint).isGreaterThan(beforeHitPoint);
    }

    @Test
    @DisplayName("참가자가 카드를 보여준다.")
    void open() {
        Card cardOne = new Card(Suit.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(Suit.DIAMOND, CardNumber.TWO);
        Participant participant = ParticipantFixture.create(cardOne, cardTwo, List.of());

        assertThat(participant.open(2)).containsAll(List.of(cardOne, cardTwo));
    }
}
