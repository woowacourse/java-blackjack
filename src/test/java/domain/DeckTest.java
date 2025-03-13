package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("덱이 비었을 때, 카드를 뽑으려고 시도하면 예외를 반환합니다.")
    void validateEmptyDeckTest() {
        //given
        Cards emptyCards = new Cards(new ArrayList<>());
        Deck deck = new Deck(emptyCards);

        //when & then
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드가 없습니다.");
    }
}
