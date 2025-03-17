package card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardShapeTest {

    @Test
    @DisplayName("CardShape가 같다면 true를 반환한다.")
    void test1() {
        // given
        CardShape cardShape = CardShape.HEART;

        // when
        boolean result = cardShape.isSame(CardShape.HEART);

        // then
        assertThat(result)
                .isTrue();
    }

    @Test
    @DisplayName("CardShape가 다르다면 false를 반환한다.")
    void test2() {
        // given
        CardShape cardShape = CardShape.HEART;

        // when
        boolean result = cardShape.isSame(CardShape.DIAMOND);

        // then
        assertThat(result)
                .isFalse();
    }
}
