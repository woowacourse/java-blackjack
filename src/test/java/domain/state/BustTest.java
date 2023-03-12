package domain.state;

import static org.assertj.core.api.Assertions.*;

import domain.card.CloverCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Bust 상태는 ")
class BustTest {
    @Test
    @DisplayName("hand 합계가 21을 초과하면 된다.")
    void createBustTest() {
        State state = new UserReady()
                .draw(CloverCard.KING)
                .draw(CloverCard.QUEEN)
                .draw(CloverCard.JACK);

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("드로우 할 수 없다.")
    void drawTest() {
        State state = new UserReady()
                .draw(CloverCard.KING)
                .draw(CloverCard.QUEEN)
                .draw(CloverCard.JACK);

        assertThatThrownBy(() -> state.draw(CloverCard.SIX))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("[ERROR] 게임이 종료되어 카드를 뽑을 수 없습니다.");
    }

    @Test
    @DisplayName("더 이상 stay 할 수 없다.")
    void stayTest() {
        State state = new UserReady()
                .draw(CloverCard.KING)
                .draw(CloverCard.QUEEN)
                .draw(CloverCard.JACK);

        assertThatThrownBy(state::stay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("[ERROR] 게임이 종료되어 상태를 조작할 수 없습니다.");
    }

    @Test
    @DisplayName("드로우한 카드들을 갖고 있다.")
    void getCardsTest() {
        State state = new UserReady()
                .draw(CloverCard.KING)
                .draw(CloverCard.QUEEN)
                .draw(CloverCard.JACK);

        assertThat(state.getCards()).containsExactly(CloverCard.KING, CloverCard.QUEEN, CloverCard.JACK);
    }
}
