package domain.player.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Number;
import domain.card.Shape;
import domain.player.state.Bust;
import domain.player.state.Hit;
import domain.player.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

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
    
    @Test
    @DisplayName("Hit 상태일 경우 수익률 계산 시도하면 예외가 발생한다.")
    void calculateProfit() {
        assertThatIllegalStateException()
                .isThrownBy(() -> hit.calculateProfit(1000))
                .withMessage("아직 배팅 금액을 계산할 수 없는 상태입니다.");
    }
}