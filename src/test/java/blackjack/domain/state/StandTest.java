package blackjack.domain.state;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Set;
import org.junit.jupiter.api.Test;

class StandTest {

    @Test
    void stand상태에서_hit하는_경우_예외발생() {
        final Set<Card> cards = Set.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN));
        final Stand stand = new Stand(new Cards(cards));

        assertThatThrownBy(() -> stand.hit(Card.of(SPADES, A)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Finish상태는 hit할 수 없습니다.");
    }

    @Test
    void stand상태에서_stay하는_경우_예외발생() {
        final Set<Card> cards = Set.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN));
        final Stand stand = new Stand(new Cards(cards));

        assertThatThrownBy(() -> stand.stay())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Finish상태는 stay할 수 없습니다.");
    }
}
