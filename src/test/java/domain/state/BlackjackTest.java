package domain.state;

import static org.assertj.core.api.Assertions.*;

import domain.card.CloverCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Blackjack 상태는 ")
class BlackjackTest {
    @Test
    @DisplayName("hand에 2장의 카드만으로 21을 만들면 될 수 있다.")
    void createBlackjackTest() {
        State state = new UserReady()
                .draw(CloverCard.KING)
                .draw(CloverCard.ACE);

        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("드로우 할 수 없다.")
    void drawTest() {
        State state = new UserReady()
                .draw(CloverCard.KING)
                .draw(CloverCard.ACE);

        assertThatThrownBy(() -> state.draw(CloverCard.SIX))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("[ERROR] 게임이 종료되어 카드를 뽑을 수 없습니다.");
    }

    @Test
    @DisplayName("더 이상 stay 할 수 없다.")
    void stayTest() {
        State state = new UserReady()
                .draw(CloverCard.KING)
                .draw(CloverCard.ACE);

        assertThatThrownBy(state::stay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("[ERROR] 게임이 종료되어 상태를 조작할 수 없습니다.");
    }

    @Test
    @DisplayName("드로우한 카드들을 갖고 있다.")
    void getCardsTest() {
        State state = new UserReady()
                .draw(CloverCard.KING)
                .draw(CloverCard.ACE);

        assertThat(state.getCards()).containsExactly(CloverCard.KING, CloverCard.ACE);
    }
}
