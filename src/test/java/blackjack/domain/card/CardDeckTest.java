package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    void 셔플된_카드덱_생성() {
        final CardDeck cardDeck = CardDeck.createNewShuffledCardDeck();
        assertThat(cardDeck).isInstanceOf(CardDeck.class);
    }

    @Test
    void 카드가_없을_때_카드반환_요청_시_예외발생() {
        final CardDeck cardDeck = CardDeck.createNewShuffledCardDeck();
        for (int i = 1; i <= 52; i++) {
            cardDeck.provideCard();
        }
        assertThatThrownBy(() -> cardDeck.provideCard())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드덱이 비어 카드를 제공할 수 없습니다.");
    }

    @Test
    void 카드덱에서_카드_1장_제공() {
        final CardDeck cardDeck = CardDeck.createNewShuffledCardDeck();
        assertThat(cardDeck.provideCard()).isInstanceOf(Card.class);
    }
}
