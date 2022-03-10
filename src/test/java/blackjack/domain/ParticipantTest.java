package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import blackjack.domain.participant.Participant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {


    @Test
    @DisplayName("점수를 올바르게 계산하는지 확인한다")
    void testCalculateScore() {
        Participant participant = new Participant("pobi");
        participant.addCard(new Card(Symbol.DIAMOND, Denomination.KING));
        participant.addCard(new Card(Symbol.CLOVER, Denomination.EIGHT));
        participant.addCard(new Card(Symbol.HEART, Denomination.TWO));

        assertThat(participant.getScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("더 유리한 에이스 값 선택하여 결과를 계산하는지 확인한다.")
    void calculateScoreContainsAce() {
        Participant participant = new Participant("pobi");
        participant.addCard(new Card(Symbol.CLOVER, Denomination.EIGHT));
        participant.addCard(new Card(Symbol.HEART, Denomination.TWO));
        participant.addCard(new Card(Symbol.SPADE, Denomination.ACE));
        participant.endTurn();

        assertThat(participant.getScore()).isEqualTo(21);
    }

}
