package blackjack.domain.state;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.FOUR;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayerRunningTest {

    @Test
    void 생성_시_cards가_null인_경우_예외발생() {
        assertThatThrownBy(() -> new PlayerRunning(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("cards는 null이 들어올 수 없습니다.");
    }

    @Test
    void 생성_시_score가_bust_score인_경우_예외발생() {
        final List<Card> cards = List.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN), Card.of(SPADES, JACK));
        assertThatThrownBy(() -> new PlayerRunning(new Cards(cards)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Running상태는 버스트된 카드가 들어올 수 없습니다.");
    }

    @Test
    void 게임_종료여부_반환() {
        final Cards cards = new Cards(List.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN)));
        final Running running = new PlayerRunning(cards);

        assertThat(running.isFinished()).isFalse();
    }

    @Test
    void 수익률_계산시_예외발생() {
        final Cards cards = new Cards(List.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN)));
        final Running running = new PlayerRunning(cards);

        assertThatThrownBy(() -> running.profit(1000, running))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Running상태는 수익을 계산할 수 없습니다.");
    }

    @Test
    void hit할_때_Bust가_아니면_Running_반환() {
        final Cards cards = new Cards(List.of(Card.of(SPADES, TWO), Card.of(SPADES, THREE)));
        final BlackjackGameState running = new PlayerRunning(cards);
        final BlackjackGameState nextState = running.hit(Card.of(SPADES, FOUR));

        assertThat(nextState).isInstanceOf(Running.class);
    }

    @Test
    void hit할_때_Bust면_Bust_반환() {
        final Cards cards = new Cards(List.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN)));
        final BlackjackGameState running = new PlayerRunning(cards);
        final BlackjackGameState nextState = running.hit(Card.of(SPADES, JACK));

        assertThat(nextState).isInstanceOf(Bust.class);
    }

    @Test
    void stay할_때_Stand_반환() {
        final Cards cards = new Cards(List.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN)));
        final BlackjackGameState running = new PlayerRunning(cards);
        final BlackjackGameState nextState = running.stay();

        assertThat(nextState).isInstanceOf(Stand.class);
    }

    @Test
    void stay할_때_Blackjack_반환() {
        final Cards cards = new Cards(List.of(Card.of(SPADES, A), Card.of(SPADES, KING)));
        final BlackjackGameState running = new PlayerRunning(cards);
        final BlackjackGameState nextState = running.stay();

        assertThat(nextState).isInstanceOf(Blackjack.class);
    }
}
