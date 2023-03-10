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
            Card card1 = Card.of(Suit.CLUB, Number.ACE);
            Card card2 = Card.of(Suit.CLUB, Number.JACK);
            int expectedSize = 2;

            //when
            hand.add(card1);
            hand.add(card2);

            //then
            assertThat(hand).extracting("cards", InstanceOfAssertFactories.collection(Card.class))
                    .hasSize(expectedSize)
                    .contains(card2, card1);
        }
    }

    @Nested
    class 점수계산 {
        @Test
        void should_올바른점수반환_when_calculateScore호출() {
            //given
            Hand hand = new Hand();
            hand.add(Card.of(Suit.CLUB, Number.ACE));
            hand.add(Card.of(Suit.SPADE, Number.ACE));
            int expected = 12;

            //when
            int actual = hand.calculateScore();

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class 점수가특정상태인지확인 {
        @Test
        void should_블랙잭이다_when_ace와10점을가질때만() {
            // given
            final Hand hand = new Hand();

            // when
            hand.add(Card.of(Suit.SPADE, Number.KING));
            hand.add(Card.of(Suit.SPADE, Number.ACE));

            // then
            assertThat(hand.isBlackjack()).isTrue();
        }

        @Test
        void should_버스트이다_when_점수가21점초과일때만() {
            // given
            final Hand hand = new Hand();

            // when
            hand.add(Card.of(Suit.SPADE, Number.JACK));
            hand.add(Card.of(Suit.SPADE, Number.KING));
            hand.add(Card.of(Suit.SPADE, Number.TWO));

            // then
            assertThat(hand.isBust()).isTrue();
        }
    }
}
