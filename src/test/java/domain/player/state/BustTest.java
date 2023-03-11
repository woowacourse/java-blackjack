package domain.player.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Number;
import domain.card.Shape;
import domain.player.state.Bust;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BustTest {
    private Bust state;
    
    @BeforeEach
    void setUp() {
        state = new Bust(new Hand());
    }
    
    @Test
    @DisplayName("버스트 상태일 경우 카드를 뽑는 시도를 하면 예외가 발생한다.")
    void draw_exception() {
        assertThatIllegalStateException()
                .isThrownBy(() -> state.draw(new Card(Shape.HEART, Number.NINE)))
                .withMessage("더이상 카드를 뽑을 수 없습니다.");
    }
    
    @Test
    @DisplayName("BlackJack 상태일 때 수익률 계산하면 -1을 곱해서 반환한다.")
    void calculateProfit() {
        assertThat(state.calculateProfit(1000))
                .isEqualTo(-1000.0);
    }
}