package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    void 문양과_숫자가_같으면_같은_카드이다() {
        Card card = new Card(Denomination.ACE, Suit.HEART);

        assertThat(card).isEqualTo(new Card(Denomination.ACE, Suit.HEART));
    }
}
