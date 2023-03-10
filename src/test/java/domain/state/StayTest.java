package domain.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class StayTest {
    private State stay;
    
    @BeforeEach
    void setUp() {
        stay = new Stay(new Hand());
    }
    
    @Test
    void draw_exception() {
        assertThatIllegalStateException()
                .isThrownBy(() -> stay.draw(new Card(Shape.HEART, Number.NINE)))
                .withMessage("더이상 카드를 뽑을 수 없는 상태입니다.");
    }
    
    @Test
    void calculateProfit() {
        assertThat(stay.calculateProfit(1000))
                .isEqualTo(1000.0);
    }
}
