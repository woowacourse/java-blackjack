package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ShapeTest {

    @Test
    @DisplayName("문양 생성 성공")
    void createShape() {
        Shape shape = Shape.valueOf("SPADE");
        assertThat(shape).isEqualTo(Shape.SPADE);
    }
}
