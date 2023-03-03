package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.collection;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    @Nested
    class 카드받기 {
        @Test
        void should_카드를패에추가한다_when_receiveCard호출() {
            //given
            Participant participant = spy(Participant.class);
            Card card = new Card(Suit.SPADE, Number.ACE);

            //when
            participant.receiveCard(card);

            //then
            assertThat(participant).extracting("hand")
                    .extracting("cards", collection(Card.class))
                    .hasSize(1)
                    .containsExactly(card);
        }
    }
}
