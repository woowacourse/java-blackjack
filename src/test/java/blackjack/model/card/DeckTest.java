package blackjack.model.card;

import static blackjack.model.card.CardFixtures.NO_SHUFFLER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("덱 테스트")
class DeckTest {

    @DisplayName("카드 한장을 뽑는다.")
    @Test
    void drawTest() {
        // given
        Deck deck = Deck.createStandardDeck(NO_SHUFFLER);

        // when
        deck.draw();

        // then
        assertThat(deck.size())
                .isSameAs(51);
    }

    @DisplayName("더이상 뽑을 카드가 없는 경우 예외가 발생한다.")
    @Test
    void shouldThrowException_WhenRunOutOfCard() {
        // given
        Deck deck = Deck.createDeckByCards(List.of(), NO_SHUFFLER);

        // when, then
        assertThatCode(deck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("더이상 뽑을 카드가 없습니다.");
    }

    @DisplayName("섞는 방법에 따라 카드를 섞어서 덱을 만든다.")
    @Test
    void createShuffleDeckTest() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADES, CardValue.ACE));
        cards.add(new Card(Suit.SPADES, CardValue.KING));

        // when
        Deck deck = Deck.createDeckByCards(cards, Collections::reverse);

        // then
        assertThat(deck.draw())
                .isEqualTo(new Card(Suit.SPADES, CardValue.KING));
        assertThat(deck.draw())
                .isEqualTo(new Card(Suit.SPADES, CardValue.ACE));
    }
}
