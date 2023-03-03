package domain;

import static org.assertj.core.api.Assertions.*;

import domain.Card;
import domain.Deck;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DeckTest {
    @DisplayName("카드 생성 후 52장 반환 확인")
    @Test
    void 카드에서_생성_후_52장_반환_확인() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("덱이 비었습니다.");
    }

    @DisplayName("중복된 카드 반환 없는지 확인")
    @Test
    void 중복된_카드_반환_확인() {
        // given
        Deck deck = new Deck();
        Set<Card> cardsDeduplicated = new HashSet<>();
        List<Card> cards = new ArrayList<>();
        // when
        for (int i = 0; i < 52; i++) {
            Card card = deck.draw();
            cardsDeduplicated.add(card);
            cards.add(card);
        }
        // then
        assertThat(cardsDeduplicated.size()).isEqualTo(cards.size());
    }
}
