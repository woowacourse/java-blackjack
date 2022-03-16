package blackjack.domain.card;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    void 생성_시_cards가_null인_경우_예외발생() {
        assertThatThrownBy(() -> new Cards(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("cards는 null이 들어올 수 없습니다.");
    }

    @Test
    void 생성_시_cards크기가_2미만인_경우_예외발생() {
        assertThatThrownBy(() -> new Cards(Set.of(Card.of(SPADES, A))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("cards는 2장이상이 들어와야 합니다.");
    }
}
