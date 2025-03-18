package card;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DeckTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void 덱에서_카드를_뽑는다(int value) {
        // given & when
        Deck deck = DeckGenerator.generateDeck();
        List<Card> card = deck.drawCards(value);

        // then
        Assertions.assertThat(card.size()).isEqualTo(value);
    }
}
