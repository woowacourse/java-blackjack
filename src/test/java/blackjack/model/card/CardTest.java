package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void 타입과_숫자를_알려주면_카드를_생성한다() {
        Card card = new Card(CardType.SPADE, CardNumber.ACE);

        assertThat(card).isEqualTo(new Card(CardType.SPADE, CardNumber.ACE));
    }

}
