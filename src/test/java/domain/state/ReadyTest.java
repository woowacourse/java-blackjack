package domain.state;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import domain.card.CloverCard;
import domain.card.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


@DisplayName("Ready 상태는 ")
class ReadyTest {
    @Test
    @DisplayName("패가 0장인 상태로 생성된다.")
    void createReadyTest() {
        Ready ready = new Ready();
        assertThat(ready.getCards()).isEmpty();
    }

    @Test
    @DisplayName("패를 1장 드로우한 뒤 Ready 상태를 유지한다.")
    void afterDrawOnceTest() {
        State state = new Ready().draw(CloverCard.FOUR);

        assertThat(state).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("패를 드로우한 뒤 hand에 넣는다.")
    void drawTest() {
        State state = new Ready()
                .draw(CloverCard.FOUR)
                .draw(CloverCard.FIVE);

        assertThat(state.getCards()).containsExactly(CloverCard.FOUR, CloverCard.FIVE);
    }

    @Test
    @DisplayName("아직 stay 할 수 없다.")
    void stayTest() {
        State ready = new Ready()
                .draw(CloverCard.FOUR);

        assertThatThrownBy(ready::stay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("[ERROR] 게임 준비 단계에는 상태를 조작할 수 없습니다.");
    }

    @Test
    @DisplayName("패를 2장 드로우한 뒤 Hit 상태가 된다.")
    void transferToHitTest() {
        Ready ready = new Ready();

        State state = ready.draw(CloverCard.FOUR).draw(CloverCard.FIVE);

        assertThat(state).isInstanceOf(Hit.class);
    }
}
