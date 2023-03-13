package blackjack.domain.card;

import static org.junit.jupiter.api.Assertions.assertThrows;

import blackjack.exception.NoMoreCardException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    @DisplayName("카드 52장이 생성되는지 테스트")
    void pick_random_card() {
        CardDeck cardDeck = CardDeck.create();
        for (int i = 0; i < 52; i++) {
            cardDeck.pick();
        }
        assertThrows(NoMoreCardException.class, () -> cardDeck.pick());
    }
}
