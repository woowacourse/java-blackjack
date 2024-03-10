package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("카드 한장을 뽑을 수 있다.")
    @Test
    void draw() {
        Card card = new Card(CardRank.EIGHT, CardShape.CLOVER);
        Deck deck = new Deck(List.of(card));

        Card result = deck.draw();

        assertThat(result).isEqualTo(card);
    }

    @DisplayName("중복 카드는 존재할 수 없다.")
    @Test
    void validateDuplicated() {
        Card card = new Card(CardRank.EIGHT, CardShape.DIAMOND);
        List<Card> cards = List.of(card, card);

        assertThatThrownBy(() -> new Deck(cards))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드가 존재하지 않으면, 더 이상 뽑을 수 없다.")
    @Test
    void drawEmpty() {
        Card card = new Card(CardRank.EIGHT, CardShape.CLOVER);
        Deck deck = new Deck(List.of(card));
        deck.draw();

        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class);
    }
}
