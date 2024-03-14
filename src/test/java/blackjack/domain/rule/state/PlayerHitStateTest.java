package blackjack.domain.rule.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Hands;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerHitStateTest {

    final State playerHitScore20 = new PlayerHitState(new Hands(List.of(
            Card.of(CardNumber.QUEEN, CardShape.DIA),
            Card.of(CardNumber.TEN, CardShape.DIA))), 0);

    @DisplayName("카드 2장으로 시작하고 Blackjack이면 Blackjack 상태이다.")
    @Test
    void startBlackjack() {
        final State state = PlayerHitState.start(Card.of(CardNumber.ACE, CardShape.DIA),
                Card.of(CardNumber.TEN, CardShape.DIA));

        assertThat(state).isInstanceOf(BlackjackState.class);
    }

    @DisplayName("카드 2장으로 시작하고 Blackjack이 아니면 PlayerHit 상태이다.")
    @Test
    void startHit() {
        final State state = PlayerHitState.start(Card.of(CardNumber.QUEEN, CardShape.DIA),
                Card.of(CardNumber.TEN, CardShape.DIA));

        assertThat(state).isInstanceOf(PlayerHitState.class);
    }

    @DisplayName("카드를 뽑아서 점수가 21초과이면 Burst 상태이다.")
    @Test
    void drawBurst() {
        // given
        final Card card = Card.of(CardNumber.TWO, CardShape.DIA);

        // when
        final State newState = playerHitScore20.draw(card);

        // then
        assertThat(newState).isInstanceOf(BustState.class);
    }

    @DisplayName("카드를 뽑아서 점수가 21이하이면 PlayerHit 상태이다.")
    @Test
    void drawPlayerHit() {
        // given
        final Card card = Card.of(CardNumber.ACE, CardShape.DIA);

        // when
        final State newState = playerHitScore20.draw(card);

        // then
        assertThat(newState).isInstanceOf(PlayerHitState.class);
    }

    @DisplayName("stand를 호출하면 Stand 상태이다.")
    @Test
    void stand() {
        final State newState = playerHitScore20.stand();

        assertThat(newState).isInstanceOf(StandState.class);
    }

    @DisplayName("Hit 상태에서 배팅 레버리지를 구할 수 없다.")
    @Test
    void calculateBetLeverage() {
        State dealerState = DealerHitState.start(Card.of(CardNumber.QUEEN, CardShape.DIA),
                Card.of(CardNumber.KING, CardShape.DIA));

        assertThatThrownBy(() -> playerHitScore20.calculateBetLeverage(dealerState))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
