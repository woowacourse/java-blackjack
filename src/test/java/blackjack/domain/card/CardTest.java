package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void 중복없는_52개의_카드를_생성() {
        final List<Card> cards = Card.cards();
        assertThat(new HashSet<>(cards)).hasSize(52);
    }

    @Test
    void 문양과_숫자를_가진_카드_생성() {
        final Suit suit = Suit.SPADES;
        final Denomination denomination = Denomination.EIGHT;
        assertThat(Card.of(suit, denomination)).isInstanceOf(Card.class);
    }
}
