package domain.player.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Number;
import domain.card.Shape;
import domain.player.state.State;
import domain.player.state.Stay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Stay 상태에서 드로우할 경우 예외가 발생한다.")
    void draw_exception() {
        assertThatIllegalStateException()
                .isThrownBy(() -> stay.draw(new Card(Shape.HEART, Number.NINE)))
                .withMessage("더이상 카드를 뽑을 수 없습니다.");
    }
    
    @Test
    @DisplayName("Stay 상태일 때 수익률 계산하면 그대로 반환한다.")
    void calculateProfit() {
        assertThat(stay.calculateProfit(1000))
                .isEqualTo(1000.0);
    }
}
