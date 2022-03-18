package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;
import blackjack.util.BlackjackTestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("Blackjack 상태에서는 hit할 수 없다.")
    void cannotHit() {
        // given
        Cards cards = new Cards(BlackjackTestUtil.createCards(21));
        State blackjack = new Blackjack(cards);

        // then
        assertThatThrownBy(() -> blackjack.hit(Card.of(Pattern.CLOVER, Denomination.ACE)))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
