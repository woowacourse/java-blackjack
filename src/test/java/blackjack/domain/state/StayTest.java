package blackjack.domain.state;

import blackjack.domain.card.HoldCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StayTest {

    @Test
    @DisplayName("Stay는 배팅금액의 1배를 얻는다.")
    void blackjackRate() {
        Stay bust = new Stay(new HoldCards());

        assertThat(bust.earningRate()).isEqualTo(1);
    }

}
