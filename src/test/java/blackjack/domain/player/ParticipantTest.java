package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import fixture.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @DisplayName("21을 초과하면 버스트다.")
    @Test
    void testIsBust() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();
        hand.append(new Card(CardRank.ACE, CardSuit.HEART));

        TestParticipant participant = new TestParticipant(hand);

        // when & then
        assertThat(participant.isBust()).isTrue();
    }

    @DisplayName("21을 초과하지 않으면 버스트가 아니다.")
    @Test
    void testIsNotBust() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();

        TestParticipant participant = new TestParticipant(hand);

        // when & then
        assertThat(participant.isBust()).isFalse();
    }

    private static class TestParticipant extends Participant {

        public TestParticipant(Hand hand) {
            super(hand);
        }

        @Override
        protected boolean canHit(int score) {
            return false;
        }
    }
}
