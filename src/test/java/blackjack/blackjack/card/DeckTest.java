package blackjack.blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("셔플된 덱을 생성한다")
    void createShuffledDeck() {
        // Given

        // When
        Assertions.assertThatCode(Deck::shuffled)
                .doesNotThrowAnyException();
    }

    @DisplayName("카드를 주어진 개수만큼 뽑는다.")
    @Test
    void drawCardsByCount() {
        // given
        final Deck deck = new Deck(() -> new ArrayDeque<>(Arrays.asList(
                new Card(Suit.CLOB, Denomination.A),
                new Card(Suit.DIAMOND, Denomination.SIX))));

        // when
        final Hand hand = deck.drawCardsByCount(2);

        // then
        assertAll(
                () -> assertThat(hand.getFirstCard()).isEqualTo(new Card(Suit.CLOB, Denomination.A)),
                () -> assertThat(hand.getPartialHand(1, hand.getSize())).isEqualTo(
                        new Hand(List.of(new Card(Suit.DIAMOND, Denomination.SIX))))
        );
    }

    @Test
    void 카드를_뽑는다() {
        // Given
        final Deck deck = Deck.shuffled();

        // When & Then
        assertThat(deck.drawCard()).isInstanceOf(Card.class);
    }

    @Test
    void 카드가_더이상_없으면_예외가_발생한다() {
        // Given
        final Deck deck = new Deck(() -> new ArrayDeque<>(List.of(
                new Card(Suit.CLOB, Denomination.A))));
        deck.drawCard();

        // When & Then
        Assertions.assertThatThrownBy(deck::drawCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("[ERROR] 카드가 더이상 없습니다.");
    }
}
