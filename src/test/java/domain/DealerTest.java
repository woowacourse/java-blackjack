package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Nested
    class 카드분배 {
        @Test
        void should_카드를더받을수있다_when_점수가17점보다작으면() {
            //given
            Dealer dealer = new Dealer();
            dealer.receiveCard(Card.of(Suit.SPADE, Number.TEN));
            dealer.receiveCard(Card.of(Suit.SPADE, Number.SIX));

            //when
            boolean drawable = dealer.shouldDrawCard();

            //then
            assertThat(drawable).isTrue();
        }

        @Test
        void should_카드를더받을수없다_when_점수가17점이상일경우() {
            //given
            Dealer dealer = new Dealer();
            dealer.receiveCard(Card.of(Suit.SPADE, Number.TEN));
            dealer.receiveCard(Card.of(Suit.SPADE, Number.SEVEN));

            //when
            boolean drawable = dealer.shouldDrawCard();

            //then
            assertThat(drawable).isFalse();
        }
    }

}