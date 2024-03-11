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
        participant.hit(card);

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
        participant.hit(card);

        // then
        assertThat(participant.getHand().getCards()).doesNotContain(card);
    }

    @DisplayName("21을 초과하지 않으면 버스트가 아니다.")
    @Test
    void testIsNotBust() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();

        Participant participant = new Participant(hand) {
            @Override
            public boolean canHit() {
                return false;
            }
        };

        // when & then
        assertThat(participant.isNotBust()).isTrue();
    }

    @DisplayName("21을 초과하면 버스트다.")
    @Test
    void testIsBust() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();
        hand.append(new Card(CardRank.ACE, CardSuit.HEART));

        Participant participant = new Participant(hand) {
            @Override
            public boolean canHit() {
                return false;
            }
        };

        // when & then
        assertThat(participant.isNotBust()).isFalse();
    }
}
