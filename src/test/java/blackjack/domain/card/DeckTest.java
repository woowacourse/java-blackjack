package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class DeckTest {

    @Test
    @DisplayName("카드 덱 생성 테스트")
    void generateDeckTest() {
        assertThatCode(Deck::new).doesNotThrowAnyException();
    }
}
