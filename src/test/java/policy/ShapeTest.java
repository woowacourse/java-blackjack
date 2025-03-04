package policy;

import static org.assertj.core.api.Assertions.assertThat;
import static policy.Shape.SPADE;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ShapeTest {

    @ParameterizedTest
    @ValueSource(strings = {"스페이드"})
    @DisplayName("문양 확인 테스트")
    void shapeTest(String shape) {
        assertThat(Shape.check(shape)).isEqualTo(SPADE);
    }
}
