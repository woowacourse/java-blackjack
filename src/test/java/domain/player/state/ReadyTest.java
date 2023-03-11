package domain.player.state;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class ReadyTest {
    private State ready;
    
    @BeforeEach
    void setUp() {
        ready = new Ready();
    }
    
    @Test
    @DisplayName("카드 갯수가 2개 미만인 경우 Ready상태를 유지한다.")
    void ready_state() {
        final State state = ready
                .draw(new Card(Shape.HEART, Number.QUEEN));
        
        assertThat(state).isExactlyInstanceOf(Ready.class);
    }
    
    @Test
    @DisplayName("스코어가 21을 넘지 않은 경우 카드를 뽑을 수 있다.")
    void hit_state() {
        final State state = ready
                .draw(new Card(Shape.HEART, Number.QUEEN))
                .draw(new Card(Shape.HEART, Number.JACK));
        
        assertThat(state).isExactlyInstanceOf(Hit.class);
    }
    
    @Test
    @DisplayName("처음 두 장의 카드 합이 21이 되면 블랙잭이다.")
    void blackjack_state() {
        final State state = ready
                .draw(new Card(Shape.HEART, Number.QUEEN))
                .draw(new Card(Shape.HEART, Number.ACE));
        
        assertThat(state).isExactlyInstanceOf(BlackJack.class);
    }
    
    @Test
    @DisplayName("Ready 상태일 경우 수익률 계산 시도하면 예외가 발생한다.")
    void calculateProfit() {
        assertThatIllegalStateException()
                .isThrownBy(() -> ready.calculateProfit(1000))
                .withMessage("아직 배팅 금액을 계산할 수 없는 상태입니다.");
    }
}
