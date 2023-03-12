package domain.state;

import static org.assertj.core.api.Assertions.*;

import domain.card.CloverCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("DealerHit 상태는")
class DealerHitTest {
    @Test
    @DisplayName("DealerReady 상태에서 2장을 드로우하면 될 수 있다.")
    void createDealerHitTest() {
        State state = new DealerReady()
                .draw(CloverCard.FOUR)
                .draw(CloverCard.FIVE);

        assertThat(state).isInstanceOf(DealerHit.class);
    }

    @Test
    @DisplayName("카드를 한 장 드로우 한 뒤 Bust 상태가 될 수 있다.")
    void transferToBustTest() {
        State state = new DealerReady()
                .draw(CloverCard.KING)
                .draw(CloverCard.FIVE);

        assertThat(state.draw(CloverCard.JACK)).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("hand 총합이 16을 초과해야 Stay 상태가 될 수 있다.")
    void transferToStayTest() {
        State state = new DealerReady()
                .draw(CloverCard.KING)
                .draw(CloverCard.QUEEN);

        assertThat(state.stay()).isInstanceOf(Stay.class);
    }
}
