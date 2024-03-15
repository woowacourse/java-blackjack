package blackjack.domain.rule.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitStateTest {

    @DisplayName("카드를 1장 뽑으면 Init 상태이다.")
    @Test
    void init() {
        final State state = new InitState()
                .draw(Card.of(CardNumber.ACE, CardShape.DIA));

        assertThat(state).isInstanceOf(InitState.class);
    }

    @DisplayName("카드 2장을 뽑고 Blackjack이면 Blackjack 상태이다.")
    @Test
    void startBlackjack() {
        final State state = new InitState().draw(Card.of(CardNumber.ACE, CardShape.DIA))
                        .draw(Card.of(CardNumber.TEN, CardShape.DIA));

        assertThat(state).isInstanceOf(BlackjackState.class);
    }

    @DisplayName("카드 2장을 뽑고 Blackjack이 아니면 Hit 상태이다.")
    @Test
    void startHit() {
        final State state = new InitState().draw(Card.of(CardNumber.QUEEN, CardShape.DIA))
                .draw(Card.of(CardNumber.TEN, CardShape.DIA));

        assertThat(state).isInstanceOf(HitState.class);
    }
}
