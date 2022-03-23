package blackjack.domain.state.finished;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.cards.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustTest {

    @Test
    @DisplayName("블랙잭/버스트 여부 검증")
    void earningRate() {
        Bust bust = new Bust(new Cards());
        assertAll(
                () -> assertThat(bust.isBlackjack())
                        .isFalse(),
                () -> assertThat(bust.isBust())
                        .isTrue()
        );
    }
}
