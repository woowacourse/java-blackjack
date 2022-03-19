package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import blackjack.domain.card.HoldCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustTest {

    @Test
    @DisplayName("Bust는 배팅금액의 -1배를 얻는다.")
    void bustRate() {
        Bust bust = new Bust(new HoldCards());

        assertThat(bust.earningRate()).isEqualTo(-1);
    }

}
