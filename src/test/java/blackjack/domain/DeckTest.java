package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("카드 덱 52개 생성 성공")
    @Test
    public void new_deckCreate_valid() {
        Deck deck = new Deck();
        assertThat(deck.getCards()).hasSize(52);
    }
}
