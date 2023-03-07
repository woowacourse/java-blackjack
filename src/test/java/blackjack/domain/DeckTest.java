package blackjack.domain;

import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class DeckTest {

    @Test
    @DisplayName("트럼프 기본 카드 생성 테스트")
    void generateTrumpSuccess() {
        assertThatCode(Deck::new).doesNotThrowAnyException();
    }

}
