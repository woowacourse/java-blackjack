package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ShapeTest {

    @Test
    @DisplayName("findAllCardPattern()은 호출하면 모든 CardPattern을 반환한다")
    void findAllCardPattern_whenCall_thenReturnCardPatterns() {
        // given
        final List<Shape> expected = Arrays.asList(Shape.values());

        // when
        final List<Shape> actual = Shape.findAllCardPattern();

        // then
        assertThat(actual.size())
                .isSameAs(expected.size());

        assertThat(actual)
                .isEqualTo(expected);
    }
}
