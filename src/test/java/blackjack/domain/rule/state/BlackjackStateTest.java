package blackjack.domain.rule.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Hands;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackStateTest {

    @DisplayName("Blackjack상태에서 카드를 뽑으면 PlayerHitState이다.")
    @Test
    void draw() {
        // given
        final State blackjackState = new BlackjackState(new Hands(List.of(
                Card.of(CardNumber.ACE, CardShape.DIA),
                Card.of(CardNumber.TEN, CardShape.DIA))));

        // when
        final State newState = blackjackState.draw(Card.of(CardNumber.QUEEN, CardShape.DIA));

        // then
        assertThat(newState).isInstanceOf(PlayerHitState.class);
    }

    @DisplayName("Blackjack상태에서 stand호출하면 Blackjack 상태이다.")
    @Test
    void stand() {
        // given
        final State blackjackState = new BlackjackState(new Hands(List.of(
                Card.of(CardNumber.ACE, CardShape.DIA),
                Card.of(CardNumber.TEN, CardShape.DIA))));

        // when
        final State newState = blackjackState.stand();

        // then
        assertThat(newState).isInstanceOf(BlackjackState.class);
    }

    @DisplayName("딜러도 Blackjack상태이면 무승부 다.")
    @Test
    void calculateBetLeverageTie() {
        // given
        final State playerState = new BlackjackState(new Hands(List.of(
                Card.of(CardNumber.ACE, CardShape.DIA),
                Card.of(CardNumber.TEN, CardShape.DIA))));

        final State dealerState = new BlackjackState(new Hands(List.of(
                Card.of(CardNumber.ACE, CardShape.SPADE),
                Card.of(CardNumber.TEN, CardShape.SPADE))));

        // when
        final BetLeverage betLeverage = playerState.calculateBetLeverage(dealerState);

        // then
        assertThat(betLeverage).isEqualTo(BetLeverage.TIE);
    }

    @DisplayName("딜러가 Blackjack상태가 아니면 LUCKY 다.")
    @Test
    void calculateBetLeverageLucky() {
        // given
        final State playerState = new BlackjackState(new Hands(List.of(
                Card.of(CardNumber.ACE, CardShape.DIA),
                Card.of(CardNumber.TEN, CardShape.DIA))));

        final State dealerState = DealerHitState.start(Card.of(CardNumber.ACE, CardShape.SPADE),
                Card.of(CardNumber.NINE, CardShape.SPADE));

        // when
        final BetLeverage betLeverage = playerState.calculateBetLeverage(dealerState);

        // then
        assertThat(betLeverage).isEqualTo(BetLeverage.LUCKY);
    }
}
