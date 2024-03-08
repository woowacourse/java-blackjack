package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    void 카드를_생성한다() {
        Card card = new Card(Denomination.ACE, Emblem.HEART);

        assertThat(card).isEqualTo(new Card(Denomination.ACE, Emblem.HEART));
    }
}
