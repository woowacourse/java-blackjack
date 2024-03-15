package blackjack.domain.rule.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Hands;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitStateTest {

    final State playerHitScore20 = new HitState(new Hands(List.of(
            Card.of(CardNumber.QUEEN, CardShape.DIA),
            Card.of(CardNumber.TEN, CardShape.DIA))));

    @DisplayName("Hit상태에서 카드를 뽑고 점수가 21이하이면 Hit 상태이다.")
    @Test
    void startBlackjack() {
        // given
        final State state = new HitState(new Hands(List.of(Card.of(CardNumber.ACE, CardShape.DIA),
                Card.of(CardNumber.TEN, CardShape.DIA))));

        // when
        final State newState = state.draw(Card.of(CardNumber.QUEEN, CardShape.HEART));

        // then
        assertThat(newState).isInstanceOf(HitState.class);
    }

    @DisplayName("Hit상태에서 카드를 뽑고 점수가 21초과이면 Bust 상태이다.")
    @Test
    void startHit() {
        // given
        final State state = new HitState(new Hands(List.of(Card.of(CardNumber.KING, CardShape.DIA),
                Card.of(CardNumber.TEN, CardShape.DIA))));

        // when
        final State newState = state.draw(Card.of(CardNumber.QUEEN, CardShape.HEART));

        // then
        assertThat(newState).isInstanceOf(BustState.class);
    }

    @DisplayName("stand를 호출하면 Stand 상태이다.")
    @Test
    void stand() {
        final State newState = playerHitScore20.stand();

        assertThat(newState).isInstanceOf(StandState.class);
    }
}
