package blackjack.domain.state;

import blackjack.domain.Hand;
import blackjack.domain.TestSetUp;
import blackjack.exception.InvalidNameInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HitTest {

    @DisplayName("BUST, STAY 상태 변환 검증")
    @Test
    void checkState() throws InvalidNameInputException {
        State hit = new Hit();
        Hand bustHand = TestSetUp.createBustPlayer().getHand();
        assertThat(hit.update(bustHand)).isInstanceOf(Bust.class);
        assertThat(hit.stay()).isInstanceOf(Stay.class);
    }
}