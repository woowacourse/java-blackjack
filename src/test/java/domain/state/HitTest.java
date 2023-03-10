package domain.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HitTest {
    private State hit;
    
    @BeforeEach
    void setUp() {
        hit = new Hit(new Hand());
    }
    
    @Test
    @DisplayName("스코어가 21을 넘지 않은 경우 카드를 뽑을 수 있다.")
    void hit_state() {
        final State state = hit
                .draw(new Card(Shape.HEART, Number.QUEEN))
                .draw(new Card(Shape.HEART, Number.JACK))
                .draw(new Card(Shape.HEART, Number.ACE));
        
        assertThat(state).isExactlyInstanceOf(Hit.class);
    }
    
    @Test
    @DisplayName("스코어가 21을 넘은 경우 카드를 뽑을 수 없다.")
    void bust_state() {
        final State state = hit
                .draw(new Card(Shape.HEART, Number.QUEEN))
                .draw(new Card(Shape.HEART, Number.JACK))
                .draw(new Card(Shape.HEART, Number.TWO));
        
        assertThat(state).isExactlyInstanceOf(Bust.class);
    }
}