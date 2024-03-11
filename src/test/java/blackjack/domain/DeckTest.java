package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    @DisplayName("랜덤 카드를 선택한다.")
    void pickRandomCardTest() {
        Deck deck = new Deck();
        assertThat(deck.pickRandomCard())
                .isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("52장 카드 생성한다.")
    void makeAllCardTest() {
        Deck deck = new Deck();

        assertThat(deck.size()).isEqualTo(52);
    }
}
