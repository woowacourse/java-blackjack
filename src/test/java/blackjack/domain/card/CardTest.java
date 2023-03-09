package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CardTest {

    @Test
    @DisplayName("카드 생성 테스트")
    void createCard() {
        assertDoesNotThrow(() -> new Card(Suit.SPADE, Denomination.ACE));
    }
}
