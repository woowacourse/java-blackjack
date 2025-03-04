package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("덱 테스트")
class DeckTest {

    @DisplayName("카드 한장을 뽑는다.")
    @Test
    void drawTest() {
        // given
        Queue<Card> cards = new LinkedList<>();
        cards.add(new Card(Suit.SPADES, CardValue.ACE));
        Deck deck = new Deck(cards);
        int expected = deck.size() - 1;

        // when
        deck.draw();

        // then
        assertThat(deck.size())
                .isSameAs(expected);
    }

    @DisplayName("더이상 뽑을 카드가 없는 경우 예외가 발생한다.")
    @Test
    void shouldThrowException_WhenRunOutOfCard() {
        // given
        Queue<Card> cards = new LinkedList<>();
        Deck deck = new Deck(cards);

        // when, then
        assertThatCode(deck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("더이상 뽑을 카드가 없습니다.");
    }
}
