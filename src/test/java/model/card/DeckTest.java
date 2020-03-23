package model.card;

import exception.EmptyDeckException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {
    public static final int DECK_SIZE = 52;
    private Deck deck;

    @BeforeEach
    void init_deck() {
        deck = new Deck(CardFactory.createCardList());
    }

    @Test
    @DisplayName("드로우 할 카드가 존재하지 않을 때")
    void drawTest() {
        assertThatThrownBy(() -> deck.draw(DECK_SIZE + 1))
            .isInstanceOf(EmptyDeckException.class)
            .hasMessageMatching("53장 이상 draw 할 카드가 존재하지 않습니다.");
    }

    @ParameterizedTest
    @DisplayName("카드를 드로우 하고 사이즈 측정")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void check_CardSize_Test(int count) {
        deck.draw(count);
        assertThat(deck.getCurrentDeckSize()).isEqualTo(DECK_SIZE - count);
    }
}