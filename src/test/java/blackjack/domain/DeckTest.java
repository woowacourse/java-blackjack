package blackjack.domain;

import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @DisplayName("카드 덱 52개 생성 성공")
    @Test
    public void new_deckCreate_valid() {
        Deck deck = new Deck();
        assertThat(deck.getCards().size()).isEqualTo(52);
    }
}