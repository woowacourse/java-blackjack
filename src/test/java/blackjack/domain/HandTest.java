package blackjack.domain;

import org.junit.jupiter.api.Test;

import static blackjack.domain.CardNumber.JACK;
import static blackjack.domain.CardNumber.KING;
import static blackjack.domain.CardNumber.QUEEN;
import static blackjack.domain.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {
    @Test
    void 가진_패의_숫자의_합계를_구할_수_있다() {
        Hand hand = new Hand();
        hand.add(new Card(JACK, SPADE));
        hand.add(new Card(QUEEN, SPADE));
        hand.add(new Card(KING, SPADE));

        int sum = hand.getSum();

        assertThat(sum).isEqualTo(30);
    }
}
