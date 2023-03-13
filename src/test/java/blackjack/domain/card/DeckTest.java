package blackjack.domain.card;

import static blackjack.domain.card.Number.ACE;
import static blackjack.domain.card.Number.TWO;
import static blackjack.domain.card.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeckTest {

    @DisplayName("카드 덱에서 주어진 장수만큼 카드를 뽑는다.")
    @Test
    void should_DrawCards_As_Count() {
        Card card1 = new Card(SPADE, ACE);
        Card card2 = new Card(SPADE, TWO);
        List<Card> mockCards = List.of(card1, card2);
        Deck deck = new MockDeckGenerator(mockCards).generate();

        List<Card> cards = new ArrayList<>();
        cards.add(deck.draw());
        cards.add(deck.draw());
        assertThat(cards)
                .containsExactly(card1, card2);
    }

    @DisplayName("카드 덱에 더 이상 카드가 없다면 예외를 발생한다.")
    @Test
    void should_ThrowException_When_DrawEmptyDeck() {
        Deck deck = new Deck(new ArrayList<>());

        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("더 이상 꺼낼 카드가 없습니다.");
    }
}
