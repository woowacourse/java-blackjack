package domain.state;

import static org.assertj.core.api.Assertions.*;

import domain.card.CloverCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Stay 상태는 ")
class StayTest {
    @Test
    @DisplayName("드로우 할 수 없다.")
    void drawTest() {
        State stay = new Ready()
                .draw(CloverCard.KING)
                .draw(CloverCard.JACK)
                .stay();

        assertThatThrownBy(() -> stay.draw(CloverCard.SIX))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("[ERROR] 게임이 종료되어 카드를 뽑을 수 없습니다.");
    }

    @Test
    @DisplayName("더 이상 stay 할 수 없다.")
    void stay() {
        State stay = new Ready()
                .draw(CloverCard.KING)
                .draw(CloverCard.JACK)
                .stay();

        assertThatThrownBy(stay::stay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("[ERROR] 게임이 종료되어 상태를 조작할 수 없습니다.");
    }

    @Test
    @DisplayName("드로우한 카드들을 갖고 있다.")
    void getCards() {
        State stay = new Ready()
                .draw(CloverCard.KING)
                .draw(CloverCard.JACK)
                .stay();

        assertThat(stay.getCards()).containsExactly(CloverCard.KING, CloverCard.JACK);
    }
}
