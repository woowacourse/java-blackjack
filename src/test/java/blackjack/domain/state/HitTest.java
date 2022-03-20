package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class HitTest {

    private final Cards cards = new Cards(Set.of(Card.from(Suit.CLOVER, Denomination.JACK),
            Card.from(Suit.DIAMOND, Denomination.EIGHT)));

    @Test
    @DisplayName("Hit 상태에서 카드를 더 받아 Bust가 되는 경우 테스트")
    void hitAndIsBust() {
        final State state = new Hit(cards)
                .draw(Card.from(Suit.SPADE, Denomination.EIGHT));

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("Hit 상태에서 카드를 더 받아 Hit이 되는 경우 테스트")
    void hitAndIsHit() {
        final State state = new Hit(cards)
                .draw(Card.from(Suit.SPADE, Denomination.TWO));

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("턴이 끝난 상태로 나타내지 않는지 테스트")
    void isFinished() {
        final State state = new Hit(cards);

        assertThat(state.isFinished()).isFalse();
    }

    @Test
    @DisplayName("턴이 진행중인 상태로 나타내는지 테스트")
    void isRunning() {
        final State state = new Hit(cards);

        assertThat(state.isRunning()).isTrue();
    }
}