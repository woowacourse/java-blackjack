package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class DeckTest {
    @DisplayName("Deck 객체 생성")
    @Test
    void createCards() {
        assertThatCode(() -> Deck.create(CardFactory.generate())).doesNotThrowAnyException();
    }
}
