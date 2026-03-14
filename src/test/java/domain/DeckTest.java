package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {
    @DisplayName("덱의 카드 개수 확인 테스트 - 52장")
    @Test
    void 생성된_덱의_카드_개수_확인() {
        Deck deck = Deck.createDeck(cards -> {});
        assertThat(deck.getCards().size()).isEqualTo(52);
    }

    @DisplayName("덱에 카드가 없을 경우 예외 발생 테스트")
    @Test
    void 덱에_카드_없을_경우_예외_발생() {
        Deck deck = Deck.createDeck(cards -> {});
        List<Card> cards = deck.getCards();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertThatThrownBy(deck::draw).isInstanceOf(IllegalArgumentException.class);
    }
}
