package blackjack.domain.state;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.FOUR;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.Test;

class DealerRunningTest {

    @Test
    void hit할_때_최대_값이_21이_넘으면_Bust_반환() {
        final Cards cards = new Cards(List.of(Card.of(SPADES, SEVEN), Card.of(SPADES, EIGHT)));
        final BlackjackGameState running = DealerRunning.createDealerGameState(cards);
        final BlackjackGameState nextState = running.hit(Card.of(SPADES, A));

        assertThat(nextState).isInstanceOf(Bust.class);
    }

    @Test
    void hit할_때_최대_값이_21이_넘지않고_17이_넘으면_STAND_반환() {
        final Cards cards = new Cards(List.of(Card.of(SPADES, FOUR), Card.of(SPADES, TWO)));
        final BlackjackGameState running = DealerRunning.createDealerGameState(cards);
        final BlackjackGameState nextState = running.hit(Card.of(SPADES, A));

        assertThat(nextState).isInstanceOf(Stand.class);
    }

    @Test
    void hit할_때_최대_값이_17이_넘지않으면_Running_반환() {
        final Cards cards = new Cards(List.of(Card.of(SPADES, THREE), Card.of(SPADES, TWO)));
        final BlackjackGameState running = DealerRunning.createDealerGameState(cards);
        final BlackjackGameState nextState = running.hit(Card.of(SPADES, A));

        assertThat(nextState).isInstanceOf(DealerRunning.class);
    }

    @Test
    void stay_할_때_예외발생() {
        final Cards cards = new Cards(List.of(Card.of(SPADES, THREE), Card.of(SPADES, TWO)));
        final BlackjackGameState running = DealerRunning.createDealerGameState(cards);

        assertThatThrownBy(() -> running.stay())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러의 running 상태는 stay를 할 수 없습니다.");
    }
}
