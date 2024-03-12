package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @DisplayName("히트 조건을 만족하면 핸드에 카드를 추가한다.")
    @Test
    void testHit() {
        // given
        Participant participant = new Participant(new Hand()) {
            @Override
            public boolean canHit() {
                return true;
            }
        };

        Card card = new Card(CardRank.ACE, CardSuit.HEART);

        // when
        participant.draw(card);

        // then
        assertThat(participant.getHand().getCards()).contains(card);
    }

    @DisplayName("히트 조건을 만족하지 못하면 핸드에 카드를 추가하지 않는다.")
    @Test
    void testNotHit() {
        // given
        Participant participant = new Participant(new Hand()) {
            @Override
            public boolean canHit() {
                return false;
            }
        };

        Card card = new Card(CardRank.ACE, CardSuit.HEART);

        // when
        participant.draw(card);

        // then
        assertThat(participant.getHand().getCards()).doesNotContain(card);
    }
}
