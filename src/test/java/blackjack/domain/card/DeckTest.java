package blackjack.domain.card;

import blackjack.exception.NoCardException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {
    @Test
    @DisplayName("덱을 다 소모했을 경우를 테스트")
    void emptyDeckTest() {
        Card card1 = new Card(Suit.CLUB, Symbol.SIX);
        Card card2 = new Card(Suit.HEART, Symbol.KING);
        Deck deck = new Deck(new LinkedList<>(Arrays.asList(card1, card2)));
        deck.draw();
        deck.draw();
        assertThatThrownBy(deck::draw)
                .isInstanceOf(NoCardException.class)
                .hasMessageContaining("덱이 비었습니다");
    }

    @Test
    @DisplayName("카드를 한장 뽑기")
    void drawOneCardTest() {
        Deck deck = new Deck(CardFactory.getInstance().issueNewDeck());
        assertThat(deck.draw()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드를 여러장 뽑기")
    void drawMultipleCardTest() {
        Deck deck = new Deck(CardFactory.getInstance().issueNewDeck());
        assertThat(deck.draw(4).size()).isEqualTo(4);
    }
}
