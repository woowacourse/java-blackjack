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

    @Test
    void 카드가_2장이면서_점수가_21점일때_블랙잭() {
        Participant participant = new Participant(new Name("eian"));

        participant.addCard(new Card(TrumpSuit.HEART, TrumpNumber.KING));
        participant.addCard(new Card(TrumpSuit.SPADE, TrumpNumber.ACE));

        assertThat(participant.isBlackJack()).isTrue();
    }

    @Test
    void 점수가_21점이지만_카드가_2장이아닐경우() {
        Participant participant = new Participant(new Name("eian"));

        participant.addCard(new Card(TrumpSuit.HEART, TrumpNumber.TEN));
        participant.addCard(new Card(TrumpSuit.SPADE, TrumpNumber.FOUR));
        participant.addCard(new Card(TrumpSuit.SPADE, TrumpNumber.SEVEN));

        assertThat(participant.isBlackJack()).isFalse();
    }
}
