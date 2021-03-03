package blackjacktest.domaintest.cardtest;

import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ShapeTest {

    @Test
    @DisplayName("문양 생성 메서드")
    void createShape() {
        Shape shape = Shape.valueOf("SPADE");
        assertThat(shape).isEqualTo(Shape.SPADE);
    }
}
