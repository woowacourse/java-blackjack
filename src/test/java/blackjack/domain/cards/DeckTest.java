package blackjack.domain.cards;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.SPADE, CardValue.ACE)));
    }

    @Test
    @DisplayName("덱이 소진되었을 경우 검증")
    void notEnoughCards() {
        deck = new Deck(new ArrayList<>());
        assertThatThrownBy(() -> deck.draw())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("덱이 모두 소진되었습니다.");
    }
}
