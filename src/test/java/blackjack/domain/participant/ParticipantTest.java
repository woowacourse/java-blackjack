package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Hand;
import fixture.CardFixture;
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

        // when
        participant.draw(CardFixture.createAHeart());

        // then
        assertThat(participant.getHand().getCards()).contains(CardFixture.createAHeart());
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

        // when
        participant.draw(CardFixture.createAHeart());

        // then
        assertThat(participant.getHand().getCards()).doesNotContain(CardFixture.createAHeart());
    }
}
