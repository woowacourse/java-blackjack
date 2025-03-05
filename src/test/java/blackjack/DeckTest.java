package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("덱 테스트")
class DeckTest {

    @DisplayName("카드 한장을 뽑는다.")
    @Test
    void drawTest() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADES, CardValue.ACE));
        Deck deck = Deck.createShuffledDeck(cards, new FixedCardShuffler());
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
        List<Card> cards = new ArrayList<>();
        Deck deck = Deck.createShuffledDeck(cards, new FixedCardShuffler());

        // when, then
        assertThatCode(deck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("더이상 뽑을 카드가 없습니다.");
    }

    @DisplayName("섞는 방법에 따라 카드를 섞어서 덱을 만든다.")
    @Test
    void createShuffleDeckTest() {
        // given
        List<Card> cards = Card.createDeck();

        // when
        Deck deck = Deck.createShuffledDeck(cards, new FixedCardShuffler());

        // then
        for (Card card : cards) {
            Card drawedCard = deck.draw();
            assertThat(card)
                    .isEqualTo(drawedCard);
        }
    }
}
