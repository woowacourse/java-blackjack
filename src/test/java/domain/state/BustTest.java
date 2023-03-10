package domain.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Number;
import domain.card.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class BustTest {
    
    @Test
    @DisplayName("버스트 상태일 경우 카드를 뽑는 시도를 하면 예외가 발생한다.")
    void draw_exception() {
        Bust bust = new Bust(new Hand());
        assertThatIllegalStateException()
                .isThrownBy(() -> bust.draw(new Card(Shape.HEART, Number.NINE)))
                .withMessage("더이상 카드를 뽑을 수 없는 상태입니다.");
    }
}