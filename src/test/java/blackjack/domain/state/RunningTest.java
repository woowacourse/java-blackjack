package blackjack.domain.state;

import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Set;
import org.junit.jupiter.api.Test;

class RunningTest {

    @Test
    void 생성_시_cards가_null인_경우_예외발생() {
        assertThatThrownBy(() -> new Running(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("cards는 null이 들어올 수 없습니다.");
    }

    @Test
    void 생성_시_score가_bust_score인_경우_예외발생() {
        final Set<Card> cards = Set.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN), Card.of(SPADES, JACK));
        assertThatThrownBy(() -> new Running(new Cards(cards)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Running상태는 버스트된 카드가 들어올 수 없습니다.");
    }

    @Test
    void Running상태의_종료여부_반환() {
        final Cards cards = new Cards(Set.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN)));
        final Running running = new Running(cards);

        assertThat(running.isFinished()).isFalse();
    }
}
