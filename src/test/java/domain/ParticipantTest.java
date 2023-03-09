package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    @Nested
    class 카드받기 {
        @Test
        void should_카드를패에추가한다_when_receiveCard호출() {
            //given
            Participant participant = mock(Participant.class, withSettings().defaultAnswer(CALLS_REAL_METHODS)
                    .useConstructor(new Name("포이")));
            Card card = new Card(Suit.SPADE, Number.ACE);

            //when
            participant.receiveCard(card);
            List<Card> hand = participant.hand();

            //then
            assertThat(hand).hasSize(1)
                    .containsExactly(card);
        }
    }
}
