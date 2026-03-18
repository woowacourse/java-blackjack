package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("덱은 52장으로 구성된다.")
    void deck_size() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.distributeCard();
        }
        assertThatThrownBy(deck::distributeCard)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("덱은 셔플 후에도 52개의 카드 조합을 전부 갖고 있다.")
    void deck_has_52_unique_cards() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            cards.add(deck.distributeCard());
        }
        assertThat(cards.stream().distinct().count()).isEqualTo(52);
    }
}
