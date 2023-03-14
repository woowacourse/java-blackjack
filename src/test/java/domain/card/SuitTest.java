package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Suit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SuitTest {

    @Nested
    class 문양이름반환 {
        @Test
        void should_정상반환_when_value호출시() {
            //given
            final String expected = "다이아몬드";

            //when
            final String actual = Suit.DIAMOND.value();

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }
}
