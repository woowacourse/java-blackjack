package domain.player.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Number;
import domain.card.Shape;
import domain.player.state.BlackJack;
import domain.player.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BlackJackTest {
    private State state;
    
    @BeforeEach
    void setUp() {
        state = new BlackJack(new Hand());
    }
    
    @Test
    @DisplayName("블랙잭 상태인 경우 더이상 카드를 뽑을 수 없습니다.")
    void draw() {
        assertThatIllegalStateException()
                .isThrownBy(() -> state.draw(new Card(Shape.HEART, Number.ACE)))
                .withMessage("더이상 카드를 뽑을 수 없습니다.");
    }
    
    @Test
    @DisplayName("BlackJack 상태일 때 수익률 계산하면 1.5를 곱해서 반환한다.")
    void calculateProfit() {
        assertThat(state.calculateProfit(1000))
                .isEqualTo(1500.0);
    }
}