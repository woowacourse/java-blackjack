package domain;

import static org.assertj.core.api.Assertions.assertThat;

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
            Card card = deck.draw();

            //then
            assertThat(card).isEqualTo(expected);
        }
    }
}