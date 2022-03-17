package blackjack.domain.paticipant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 완료되지않은_딜러가_카드리스트를_반환할_때_예외발생() {
        final Dealer dealer = new Dealer(CardDeck.createNewShuffledCardDeck());

        assertThatThrownBy(() -> dealer.cards())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러는 완료되지 않으면 카드리스트를 반환하지 않습니다.");
    }
}
