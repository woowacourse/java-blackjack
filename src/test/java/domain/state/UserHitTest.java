package domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CloverCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("UserHit 상태는")
public class UserHitTest {
    @Test
    @DisplayName("Ready 상태에서 2장을 드로우하면 될 수 있다.")
    void createHitTest() {
        State state = new Ready()
                .draw(CloverCard.FOUR)
                .draw(CloverCard.FIVE);

        assertThat(state).isInstanceOf(UserHit.class);
    }

    @Test
    @DisplayName("카드를 한 장 드로우 한 뒤 Bust 상태가 될 수 있다.")
    void transferToBustTest(){
        State state = new Ready()
                .draw(CloverCard.KING)
                .draw(CloverCard.QUEEN);

        assertThat(state.draw(CloverCard.JACK)).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("카드 받기를 멈추고 Stay 상태가 될 수 있다.")
    void transferToStayTest(){
        State state = new Ready()
                .draw(CloverCard.KING)
                .draw(CloverCard.QUEEN);

        assertThat(state.stay()).isInstanceOf(Stay.class);
    }
}
