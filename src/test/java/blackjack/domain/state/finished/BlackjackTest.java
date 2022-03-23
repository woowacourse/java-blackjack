package blackjack.domain.state.finished;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.cards.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("블랙잭/버스트 여부 검증")
    void earningRate() {
        Blackjack blackjack = new Blackjack(new Cards());
        assertAll(
                () -> assertThat(blackjack.isBlackjack())
                        .isTrue(),
                () -> assertThat(blackjack.isBust())
                        .isFalse()
        );
    }
}
