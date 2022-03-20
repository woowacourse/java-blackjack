package blackjack.domain.state.finished;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("수익 배율 값 검증")
    void earningRate() {
        Blackjack blackjack = new Blackjack(new Cards());
        assertThat(blackjack.earningRate())
                .isEqualTo(1.5);
    }
}
