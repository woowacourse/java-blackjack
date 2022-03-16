package blackjack.domain.paticipant;

import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 완료되지않은_딜러가_카드리스트를_반환할_때_예외발생() {
        Cards cards = new Cards(List.of(Card.of(SPADES, TWO), Card.of(SPADES, THREE)));
        final Dealer dealer = new Dealer(cards);

        assertThatThrownBy(() -> dealer.cards())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러는 완료되지 않으면 카드리스트를 반환하지 않습니다.");
    }
}
