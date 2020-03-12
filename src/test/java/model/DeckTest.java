package model;

import exception.EmptyDeckException;
import org.junit.jupiter.api.BeforeEach;
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
    void drawTest() {
        assertThatThrownBy(() -> {
            deck.draw(DECK_SIZE+1);
        }).isInstanceOf(EmptyDeckException.class).hasMessageMatching("53장 이상 draw 할 카드가 존재하지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void check_CardSize_Test(int count) {
        deck.draw(count);
        assertThat(deck.getCurrentDeckSize()).isEqualTo(DECK_SIZE - count);
    }
}
