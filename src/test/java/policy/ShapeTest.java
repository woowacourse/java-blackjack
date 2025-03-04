package policy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ShapeTest {

    @ParameterizedTest
    @CsvSource(value = {"스페이드:SPADE", "다이아몬드:DIAMOND", "하트:HEART", "클로버:CLOVER"}, delimiter = ':')
    @DisplayName("문양 확인 테스트")
    void shapeTest(String input, Shape shape) {
        assertThat(Shape.check(input)).isEqualTo(shape);
    }
}
