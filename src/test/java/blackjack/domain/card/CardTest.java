package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void suit이_null이_들어올_경우_예외발생() {
        assertThatThrownBy(() -> new Card(null, Denomination.EIGHT))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("suit은 null이 들어올 수 없습니다.");
    }

    @Test
    void denomination이_null이_들어올_경우_예외발생() {
        assertThatThrownBy(() -> new Card(Suit.SPADES, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("denomination은 null이 들어올 수 없습니다.");
    }

    @Test
    void 문양과_숫자를_가진_카드_생성() {
        final Suit suit = Suit.SPADES;
        final Denomination denomination = Denomination.EIGHT;
        assertThat(new Card(suit, denomination)).isInstanceOf(Card.class);
    }
}
