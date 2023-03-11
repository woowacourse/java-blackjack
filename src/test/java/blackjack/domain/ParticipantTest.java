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
        Card cardOne = Card.of(Suit.DIAMOND, CardNumber.SIX);
        Card cardTwo = Card.of(Suit.HEART, CardNumber.ACE);
        Participant participant = ParticipantFixture.create(List.of(cardOne, cardTwo), List.of());
        int totalPoint = participant.getTotalPoint();

        assertThat(totalPoint).isEqualTo(17);
    }

    @Test
    @DisplayName("참가자가 카드를 뽑는다.")
    void hit() {
        Card cardOne = Card.of(Suit.DIAMOND, CardNumber.THREE);
        Card cardTwo = Card.of(Suit.DIAMOND, CardNumber.TWO);
        Participant participant = ParticipantFixture.create(List.of(cardOne, cardTwo), List.of());
        int beforeHitPoint = participant.getTotalPoint();
        participant.hit(Card.of(Suit.SPADE, CardNumber.ACE));
        int afterHitPoint = participant.getTotalPoint();

        assertThat(afterHitPoint).isGreaterThan(beforeHitPoint);
    }

    @Test
    @DisplayName("참가자가 카드를 보여준다.")
    void open() {
        Card cardOne = Card.of(Suit.DIAMOND, CardNumber.THREE);
        Card cardTwo = Card.of(Suit.DIAMOND, CardNumber.TWO);
        Participant participant = ParticipantFixture.create(List.of(cardOne, cardTwo), List.of());

        assertThat(participant.initialOpen()).containsAll(List.of(cardOne, cardTwo));
    }
}
