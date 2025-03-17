package domain.card;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("덱이 비었을 때, 카드를 뽑으려고 시도하면 예외를 반환합니다.")
    void validateEmptyDeckTest() {
        //given
        Deck deck = new Deck(new ArrayList<>());
        //when & then
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드가 없습니다.");
    }

    @Test
    @DisplayName("중복된 카드로 덱을 생성할 수 없다.")
    void noDuplicateTest() {
        // when & then
        assertThatThrownBy(() -> new Deck(List.of(Card.CLOVER_ACE, Card.CLOVER_ACE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드로 덱을 생성할 수 없습니다.");
    }
}