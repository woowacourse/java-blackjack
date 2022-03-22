package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Outcome;
import blackjack.domain.bet.BetMoney;
import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RunningTest {

    @Test
    @DisplayName("Running 상태에서 profit을 구하려고 하면 에러가 발생한다.")
    void profit_error() {
        State state = new Running() {
            @Override
            public State draw(Card card) {
                return null;
            }

            @Override
            public State stay() {
                return null;
            }
        };

        assertThatThrownBy(() -> state.profit(Outcome.WIN, new BetMoney(100)))
                .isInstanceOf(IllegalStateException.class);
    }
}
