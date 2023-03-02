package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.collection;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.withSettings;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

        @ParameterizedTest(name = "bound : {0}, drawable : {1}")
        @CsvSource (value = {"11:false", "12:true"}, delimiter = ':')
        void should_카드를추가로받을수있다_when_21점이하면(int upperBound, boolean expected) {
            //given
            Participant participant = mock(Participant.class, withSettings()
                    .useConstructor(upperBound)
                    .defaultAnswer(CALLS_REAL_METHODS));
            Card card1 = new Card(Suit.SPADE, Number.ACE);
            Card card2 = new Card(Suit.CLUB, Number.ACE);
            Card card3 = new Card(Suit.SPADE, Number.JACK);
            participant.receiveCard(card1);
            participant.receiveCard(card2);
            participant.receiveCard(card3);

            //when
            boolean actual = participant.isDrawable();

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }
}
