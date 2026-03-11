package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeckTest {
    private final int MAX_CARD_AMOUNT = 52;

    @Test
    void 덱_생성_시_52장의_고유한_카드가_생성되어야_한다() {
        Deck deck = new Deck();
        Set<Card> drawnCards = new HashSet<>();

        for (int i = 0; i < MAX_CARD_AMOUNT; i++) {
            drawnCards.add(deck.draw());
        }

        Assertions.assertThat(drawnCards).hasSize(MAX_CARD_AMOUNT);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10, 52})
    void 원하는_개수의_카드를_뽑을_수_있다(int amount) {
        Deck deck = new Deck();
        List<Card> cards = deck.drawWithAmount(amount);

        Assertions.assertThat(cards).hasSize(amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 53})
    void 범위를_벗어난_개수의_카드를_뽑으면_예외가_발생해야_한다(int amount) {
        Deck deck = new Deck();

        Assertions.assertThatThrownBy(() -> deck.drawWithAmount(amount)).isInstanceOf(IllegalArgumentException.class);
    }

}
