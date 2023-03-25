package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    private Deck deck;

    @DisplayName("하나의 덱에 카드의 수는 52개이다.")
    @Test
    void Should_Create_When_NewDeck() {
        deck = Deck.of(new ShuffleCardsGenerator().generator());

        assertThat(deck.getSize()).isEqualTo(52);
    }

    @DisplayName("덱의 가장 위쪽에 위치하는 카드를 뽑을 수 있다.")
    @Test
    void Should_Draw_When_HIT() {
        deck = Deck.of(List.of(new Card(CardNumber.ACE, CardSymbol.HEARTS)));

        assertThat(deck.draw().getCardNumberToString()).isEqualTo("A");
    }

    @DisplayName("덱이 비어있다면 true를 반환할 수 있다.")
    @Test
    void Should_ReturnTrue_When_Empty() {
        deck = Deck.of(List.of());

        assertThat(deck.isEmpty()).isTrue();
    }

    @DisplayName("덱이 비어있지 않다면 false를 반환할 수 있다.")
    @Test
    void Should_ReturnFalse_When_NotEmpty() {
        deck = Deck.of(List.of(new Card(CardNumber.ACE, CardSymbol.HEARTS)));

        assertThat(deck.isEmpty()).isFalse();
    }

    @DisplayName("덱이 비어 있는 상태에서 카드를 뽑을 경우 예외가 발생한다.")
    @Test
    void Should_ThrowException_When_DrawWhenDeckIsEmpty() {
        deck = Deck.of(List.of());

        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("더 이상 카드를 뽑을 수 없습니다.");
    }
}
