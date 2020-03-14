package domain.card;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    void tryToCreateDeckWithDuplicatedCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Symbol.ACE, Type.SPADE));
        cards.add(new Card(Symbol.ACE, Type.SPADE));
        assertThatThrownBy(() -> new Deck(cards))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("덱에 중복된 카드가 존재할 수 없습니다.");
    }
}
