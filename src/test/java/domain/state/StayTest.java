package domain.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class StayTest {
    @Test
    void draw_exception() {
        Stay stay = new Stay(new Hand());
        assertThatIllegalStateException()
                .isThrownBy(() -> stay.draw(new Card(Shape.HEART, Number.NINE)))
                .withMessage("더이상 카드를 뽑을 수 없는 상태입니다.");
    }
}
