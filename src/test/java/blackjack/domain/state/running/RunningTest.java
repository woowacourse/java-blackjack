package blackjack.domain.state.running;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.state.State;
import org.junit.jupiter.api.Test;

class RunningTest {
    Running running = new Running(new Cards()) {
        @Override
        public State draw(Card card) {
            return null;
        }

        @Override
        public State stay() {
            return null;
        }
    };

    @Test
    void isFinished() {
        assertThat(running.isFinished())
                .isFalse();
    }

    @Test
    void profit() {
        assertThatThrownBy(() -> running.profit(10000))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아직 수익을 계산할 수 없습니다.");
    }
}
