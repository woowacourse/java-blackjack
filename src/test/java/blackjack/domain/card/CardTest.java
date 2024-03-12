package blackjack.domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    @Test
    void 카드를_생성한다() {
        Card card = new Card(Denomination.ACE, Emblem.HEART);

        assertThat(card).isEqualTo(new Card(Denomination.ACE, Emblem.HEART));
    }
}
