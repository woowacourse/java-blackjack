package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DeckTest {
    @DisplayName("카드 생성 후 52장 반환 확인")
    @Test
    void 카드에서_생성_후_52장_반환_확인() {
        Deck deck = Deck.of(Arrays.stream(Symbol.values()).collect(Collectors.toList()),
                Arrays.stream(CardNumber.values()).collect(Collectors.toList()));
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertThat(deck.draw()).isInstanceOf(Card.class);
    }

    @DisplayName("중복된 카드 반환 없는지 확인")
    @Test
    void 중복된_카드_반환_확인() {
        // given
        Deck deck = Deck.of(Arrays.stream(Symbol.values()).collect(Collectors.toList()),
                Arrays.stream(CardNumber.values()).collect(Collectors.toList()));
        List<Card> cards = new ArrayList<>();
        // when
        for (int i = 0; i < 52; i++) {
            Card card = deck.draw();
            cards.add(card);
        }
        Set<Card> cardsDeduplicated = new HashSet<>(cards);

        // then
        assertThat(cardsDeduplicated.size()).isEqualTo(cards.size());
    }
}
