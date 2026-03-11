package domain.player;

import domain.card.Card;
import domain.card.TrumpNumber;
import domain.card.TrumpSuit;
import domain.vo.Name;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {

    @Test
    void 점수가_21이하면_bust가_아니다() {
        Participant participant = new Participant(new Name("eian"));

        participant.addCard(new Card(TrumpSuit.HEART, TrumpNumber.TEN));
        participant.addCard(new Card(TrumpSuit.SPADE, TrumpNumber.NINE));

        assertThat(participant.isBust()).isFalse();
    }

    @Test
    void 점수가_21초과면_bust이다() {
        Participant participant = new Participant(new Name("eian"));

        participant.addCard(new Card(TrumpSuit.HEART, TrumpNumber.KING));
        participant.addCard(new Card(TrumpSuit.SPADE, TrumpNumber.QUEEN));
        participant.addCard(new Card(TrumpSuit.CLUB, TrumpNumber.TWO));

        assertThat(participant.isBust()).isTrue();
    }
}
