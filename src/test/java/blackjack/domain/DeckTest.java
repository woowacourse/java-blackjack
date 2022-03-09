package blackjack.domain;

import static blackjack.domain.Deck.EMPTY_CARD_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("덱이 정상적으로 생성되었는지 확인")
    void create() {
        Deck deck = Deck.createFixedCards();

        assertThat(deck).isNotNull();
    }

    @Test
    @DisplayName("덱이 정상적으로 52장의 카드를 가지고 있는지 확인")
    void createFixedCards() {
        Deck deck = Deck.createFixedCards();
        List<Card> cards = deck.getCards();

        assertThat(cards.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("덱이 생성되었을때 정상적으로 카드를 주는지 확인")
    void draw() {
        Deck deck = Deck.createFixedCards();
        Card card = deck.draw();
        Card excepted = new Card(Denomination.ACE, Suit.SPADE);

        assertThat(card).isEqualTo(excepted);
    }

    @Test
    @DisplayName("카드가 모두 소진되었을 때 예외를 발생한다")
    void cardEmptyException() {
        Deck deck = Deck.createFixedCards();

        Assertions.assertThatThrownBy(() -> {
                    IntStream.range(0, 52 + 1)
                            .forEach((i) -> deck.draw());
                }).isInstanceOf(NoSuchElementException.class)
                .hasMessage(EMPTY_CARD_EXCEPTION_MESSAGE);
        ;
    }
}
