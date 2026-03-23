package domain.card;

import java.util.HashSet;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DeckTest {
    private final int MAX_CARD_AMOUNT = 52;

    @Test
    void 덱_생성_시_52장의_고유한_카드가_생성되어야_한다() {
        Deck deck = Deck.createWithAllCards();
        Set<Card> drawnCards = new HashSet<>();

        for (int i = 0; i < MAX_CARD_AMOUNT; i++) {
            drawnCards.add(deck.draw());
        }

        Assertions.assertThat(drawnCards).hasSize(MAX_CARD_AMOUNT);
    }

    @ParameterizedTest
    @ValueSource(ints = {53})
    void 범위를_벗어난_개수의_카드를_뽑으면_예외가_발생해야_한다(int amount) {
        Deck deck = Deck.createWithAllCards();

        Assertions.assertThatThrownBy(() -> {
            for (int i = 0; i < amount; i++) {
                deck.draw();
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
