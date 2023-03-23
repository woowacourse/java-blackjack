package domain.state;

import static org.assertj.core.api.Assertions.*;

import domain.card.CloverCard;
import exception.IllegalToStayInReadyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("UserReady 상태는 ")
class UserReadyTest {
    @Test
    @DisplayName("hand가 0장인 상태로 생성된다.")
    void createUserReadyTest() {
        UserReady userReady = new UserReady();
        assertThat(userReady.getCards()).isEmpty();
    }

    @Test
    @DisplayName("카드를 1장 드로우한 뒤 Ready 상태를 유지한다.")
    void afterDrawOnceTest() {
        State state = new UserReady().draw(CloverCard.FOUR);

        assertThat(state).isInstanceOf(UserReady.class);
    }

    @Test
    @DisplayName("카드를 드로우한 뒤 hand에 넣는다.")
    void drawTest() {
        State state = new UserReady()
                .draw(CloverCard.FOUR)
                .draw(CloverCard.FIVE);

        assertThat(state.getCards()).containsExactly(CloverCard.FOUR, CloverCard.FIVE);
    }

    @Test
    @DisplayName("아직 stay 할 수 없다.")
    void stayTest() {
        State state = new UserReady().draw(CloverCard.FOUR);

        assertThatThrownBy(state::stay)
                .isInstanceOf(IllegalToStayInReadyException.class)
                .hasMessageContaining("[ERROR] 게임 준비 단계에는 상태를 조작할 수 없습니다.");
    }

    @Test
    @DisplayName("패를 2장 드로우한 뒤 Hit 상태가 될 수 있다.")
    void transferToHitTest() {
        State state = new UserReady()
                .draw(CloverCard.FOUR)
                .draw(CloverCard.FIVE);

        assertThat(state).isInstanceOf(UserHit.class);
    }

    @Test
    @DisplayName("패를 2장 드로우한 뒤 Blackjack 상태가 될 수 있다.")
    void transferToBlackjackTest() {
        State state = new UserReady()
                .draw(CloverCard.KING)
                .draw(CloverCard.ACE);

        assertThat(state).isInstanceOf(Blackjack.class);
    }
}
