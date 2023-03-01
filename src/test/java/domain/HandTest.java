package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {
    @Nested
    class 카드추가 {
        @Test
        void should_정상적으로카드추가_when_add호출() {
            //given
            Hand hand = new Hand();
            Card card1 = new Card(Suit.CLUB, Number.ACE);
            Card card2 = new Card(Suit.CLUB, Number.JACK);
            int expectedSize = 2;

            //when
            hand.add(card1);
            hand.add(card2);

            //then
            assertThat(hand).extracting("cards", InstanceOfAssertFactories.collection(Card.class))
                    .hasSize(expectedSize)
                    .containsSequence(card2, card1);
        }
    }
}
