package blackjack.domain.rule.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Hands;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BurstStateTest {

    final State state = new BurstState(new Hands(List.of(
            Card.of(CardNumber.TEN, CardShape.SPADE),
            Card.of(CardNumber.QUEEN, CardShape.SPADE),
            Card.of(CardNumber.TWO, CardShape.SPADE))));

    @DisplayName("Burst 상태에서 카드를 뽑을 수 없다.")
    @Test
    void draw() {
        final Card card = Card.of(CardNumber.ACE, CardShape.DIA);
        assertThatThrownBy(() -> state.draw(card))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Burst 상태에서 stand하면 Burst 상태이다.")
    @Test
    void stand() {
        final State newState = state.stand();

        assertThat(newState).isInstanceOf(BurstState.class);
    }

    @DisplayName("Burst 상태에서 배팅 레버리지 상태는 LOSE이다.")
    @Test
    void calculateBetLeverage() {
        final State dealerState = DealerHitState.start(Card.of(CardNumber.TWO, CardShape.SPADE),
                Card.of(CardNumber.TWO, CardShape.DIA));

        final BetLeverage betLeverage = state.calculateBetLeverage(dealerState);

        assertThat(betLeverage).isEqualTo(BetLeverage.LOSE);
    }
}
