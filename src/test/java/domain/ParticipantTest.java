package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    @Nested
    class 카드받기 {
        @Test
        void should_카드를패에추가한다_when_receiveCard호출() {
            //given
            Participant participant = spy(Participant.class);
            Card card = Card.of(Suit.SPADE, Number.ACE);

            //when
            participant.receiveCard(card);
            List<Card> hand = participant.getHand();

            //then
            assertThat(hand).hasSize(1)
                    .containsExactly(card);
        }
    }
}
