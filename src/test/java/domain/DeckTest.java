package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Set;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {
    @Nested
    class 생성 {
        @Test
        void should_중복되는인스턴스가없어야한다_when_생성() {
            //given
            Deck deck = Deck.getInstance();

            //when

            //then
            assertThat(deck).extracting("cards", InstanceOfAssertFactories.collection(Set.class))
                    .size()
                    .isEqualTo(52);
        }
    }

    @Nested
    class 카드뽑기 {
        @Test
        void should_적절한카드반환_when_draw호출() {
            //given
            Deck deck = Deck.getInstance();
            Card expected = new Card(Suit.CLUB, Number.JACK);

            //when
            Card actual = deck.draw();

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class 카드섞기 {
        @Test
        void should_카드를섞는다_when_shuffle호출() {
            //given
            Deck deck = Deck.getInstance();
            Card expected = new Card(Suit.SPADE, Number.ACE);
            ShuffleStrategy testShuffleStrategy = Collections::reverse;

            //when
            deck.shuffle(testShuffleStrategy);
            Card actual = deck.draw();
            //then
            assertThat(actual).isEqualTo(expected);
        }
    }
}