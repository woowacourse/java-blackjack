package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    private Deck deck;

    @Test
    @DisplayName("Deck 생성")
    void create() {
        assertThat(Deck.create()).isNotNull();
    }

    @Test
    @DisplayName("한 장의 카드를 반환")
    void handoutSingle() {
        Deck deck = Deck.create();
        assertThat(deck.handOutCard()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("중복되지 않는 여러 장의 카드를 반환하고 덱은 모두 소비된다.")
    void handoutMultiple() {
        Deck deck = Deck.create();
        Set<Card> cards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            cards.add(deck.handOutCard());
        }
        assertThat(cards).hasSize(52);
        assertThat(deck.isEmpty()).isTrue();
    }
}
