package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SuitTest {

    @Nested
    class 문양이름반환 {
        @Test
        void test_value_should_정상반환_when_value호출시() {
            //given
            final String expected = "다이아몬드";

            //when
            final String actual = Suit.DIAMOND.value();

            //then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void test__should__when_() {
            //given

            //when

            //then
        }
    }
}
