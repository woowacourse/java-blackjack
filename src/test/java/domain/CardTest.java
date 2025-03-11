package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class CardTest {
    @RepeatedTest(52)
    @DisplayName("랜덤으로 카드를 선택할 때 최대 카드 갯수 이내이면 예외가 발생하지 않는다.")
    void testRandomCreation() {
        Deck deck = DeckGenerator.generateTestDeck();
        assertThatCode(deck::drawNewCard).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("랜덤으로 카드를 선택할 때 카드를 모두 사용하면 예외가 발생한다.")
    void testRandomCreationException() {
        // given
        Deck deck = DeckGenerator.generateTestDeck();
        for (int i = 0; i < 52; i++) {
            deck.drawNewCard();
        }
        // when && then
        assertThatThrownBy(deck::drawNewCard).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("[ERROR] 카드를 생성할 수 없습니다.");
    }
}
