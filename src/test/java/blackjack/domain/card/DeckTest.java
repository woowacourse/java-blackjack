package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    private Card card1;
    private Card card2;

    @BeforeEach
    void setUp() {
        card1 = new Card(Suit.CLOVER, Rank.ACE);
        card2 = new Card(Suit.CLOVER, Rank.TWO);
    }

    @Test
    @DisplayName("덱을 생성했을 때의 순서대로 카드가 반환되는지")
    void drawSuccess() {
        List<Card> cards = List.of(card1, card2);
        Deck deck = new Deck(cards);

        assertThat(deck.draw()).isEqualTo(card1);
        assertThat(deck.draw()).isEqualTo(card2);
    }

    @Test
    @DisplayName("빈 덱에서 카드를 뽑을 땐 예외 발생")
    void drawFail() {
        Deck deck = new Deck(List.of(card1));
        deck.draw();

        assertThatThrownBy(deck::draw)
                .isInstanceOf(NoSuchElementException.class);
    }
}