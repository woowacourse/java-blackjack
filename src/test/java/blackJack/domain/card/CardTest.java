package blackJack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void 원하는_특정_카드를_가져온다() {
        assertThat(Card.of(Symbol.SPADE, Denomination.KING)).isNotNull();
    }

    @Test
    void 원하는_특정_카드의_포인트를_가져온다() {
        Card card = Card.of(Symbol.SPADE, Denomination.KING);
        assertThat(card.getPoint()).isEqualTo(10);
    }
}
