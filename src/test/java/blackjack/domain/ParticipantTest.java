package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import blackjack.domain.participant.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {

    private ParticipantForTest participant;

    @BeforeEach
    void setParticipantForTest() {
        participant = new ParticipantForTest("pobi");
    }

    @Test
    @DisplayName("처음 두 장의 카드 합이 21일경우 블랙잭이다")
    void isBlackjackWhenTrue() {
        participant.addCard(new Card(Symbol.SPADE, Denomination.KING));
        participant.addCard(new Card(Symbol.HEART, Denomination.ACE));
        participant.computeAce();

        assertThat(participant.isBlackjack(2)).isTrue();
    }

    @Test
    @DisplayName("카드 합이 21이지만 카드가 2장 초과인 경우 블랙잭이 아니다")
    void isBlackjackWhenFalse_MoreThanTwoCards() {
        participant.addCard(new Card(Symbol.SPADE, Denomination.KING));
        participant.addCard(new Card(Symbol.HEART, Denomination.THREE));
        participant.addCard(new Card(Symbol.DIAMOND, Denomination.EIGHT));
        participant.computeAce();

        assertThat(participant.isBlackjack(2)).isFalse();
    }

    @Test
    @DisplayName("카드 합이 21이 아닐 경우 블랙잭이 아니다")
    void isBlackjackWhenFalse_NotGoalScore() {
        participant.addCard(new Card(Symbol.SPADE, Denomination.KING));
        participant.addCard(new Card(Symbol.HEART, Denomination.THREE));
        participant.computeAce();

        assertThat(participant.isBlackjack(2)).isFalse();
    }

    @Test
    @DisplayName("점수를 올바르게 계산하는지 확인한다 : 에이스가 존재하지 않는 경우")
    void testCalculateScore() {
        participant.addCard(new Card(Symbol.DIAMOND, Denomination.KING));
        participant.addCard(new Card(Symbol.CLOVER, Denomination.EIGHT));
        participant.addCard(new Card(Symbol.HEART, Denomination.TWO));
        participant.computeAce();

        assertThat(participant.getScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("더 유리한 에이스 값 선택하여 결과를 계산하는지 확인한다. - 11점 선택")
    void computeAceAsEleven() {
        participant.addCard(new Card(Symbol.CLOVER, Denomination.EIGHT));
        participant.addCard(new Card(Symbol.HEART, Denomination.TWO));
        participant.addCard(new Card(Symbol.SPADE, Denomination.ACE));
        participant.computeAce();

        assertThat(participant.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("더 유리한 에이스 값 선택하여 결과를 계산하는지 확인한다. - 1점 선택")
    void computeAceAsOne() {
        participant.addCard(new Card(Symbol.CLOVER, Denomination.KING));
        participant.addCard(new Card(Symbol.HEART, Denomination.QUEEN));
        participant.addCard(new Card(Symbol.SPADE, Denomination.ACE));
        participant.computeAce();

        assertThat(participant.getScore()).isEqualTo(21);
    }

    class ParticipantForTest extends Participant {
        ParticipantForTest(String name) {
            super(name);
        }

        public boolean isHittable() {
            return true;
        }
    }
}
