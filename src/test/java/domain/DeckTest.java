package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("생성 확인")
    void create() {
        assertThatCode(DeckFactory::createDeck)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Deck 사이즈가 52개가 아닌 경우 예외 처리")
    void createDeckWithInvalidSizeInput() {
        Queue<Card> cards = new LinkedList<>();

        assertThatThrownBy(() -> new Deck(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드의 개수가 52개가 아닙니다.");
    }

    @Test
    @DisplayName("Deck 내에 중복된 카드가 있는 경우 예외 처리")
    void createDeckWithDuplicatedInput() {
        Queue<Card> cards = new LinkedList<>();
        Card card = new Card(Symbol.SPADE, Type.QUEEN);

        for (int i = 0; i < 52; i++) {
            cards.add(card);
        }

        assertThatThrownBy(() -> new Deck(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드가 있습니다.");
    }
}