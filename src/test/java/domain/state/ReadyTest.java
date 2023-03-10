package domain.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {
    private State ready;
    
    @BeforeEach
    void setUp() {
        ready = new Ready(new Hand());
    }
    
    @Test
    void create() {
        assertThat(ready).isExactlyInstanceOf(Ready.class);
    }
    
    @Test
    @DisplayName("스코어가 21을 넘지 않은 경우 카드를 뽑을 수 있다.")
    void running_state() {
        final State state = ready
                .draw(new Card(Shape.HEART, Number.QUEEN))
                .draw(new Card(Shape.HEART, Number.JACK));
        
        assertThat(state).isExactlyInstanceOf(Hit.class);
    }
}
