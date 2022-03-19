package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import blackjack.domain.card.HoldCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("블랙잭은 배팅금액의 1.5배를 얻는다.")
    void blackjackRate() {
        Blackjack blackjack = new Blackjack(new HoldCards());

        assertThat(blackjack.earningRate()).isEqualTo(1.5);
    }
}
