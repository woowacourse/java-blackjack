package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class CardDeckTest {
    @Test
    @DisplayName("카드덱을 생성한다.")
    void createCardDeck() {
        CardGenerator generator = new CardGenerator();

        Assertions.assertDoesNotThrow(
                () -> new CardDeck(generator.generate()));
    }
    
}
