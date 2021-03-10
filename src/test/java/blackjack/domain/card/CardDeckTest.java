package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDeckTest {
    @DisplayName("덱이 생성되었는지 테스트한다.")
    @Test
    void deckCreationTest() {
        CardDeck deck = CardDeck.createDeck();
        assertThat(deck).isInstanceOf(CardDeck.class);
    }
}