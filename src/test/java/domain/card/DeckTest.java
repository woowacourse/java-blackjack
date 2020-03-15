package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {
    @Test
    @DisplayName("중복되는 카드가 있을시 예외처리")
    void validate() {
        Stack<Card> stack = new Stack<>();
        stack.push(new Card(Symbol.ACE, Type.CLOVER));
        stack.push(new Card(Symbol.ACE, Type.CLOVER));
        assertThatThrownBy(() -> new Deck(stack))
                .isInstanceOf(DeckDuplicationException.class)
                .hasMessage("중복되는 카드가 존재합니다.");
    }

    @Test
    @DisplayName("한 장의 카드를 반환")
    void dealCard() {
        Deck deck = DeckFactory.create();
        assertThat(deck.dealCard()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("두 장의 PlayingCards 반환")
    void name() {
        Deck deck = DeckFactory.create();
        assertThat(deck.dealInitCards().countCards()).isEqualTo(2);
    }

    @Test
    @DisplayName("중복되지 않는 여러 장의 카드를 반환하고 덱은 모두 소비된다.")
    void dealCardMultiple() {
        Deck deck = DeckFactory.create();
        Set<Card> cards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            cards.add(deck.dealCard());
        }
        assertThat(cards).hasSize(52);
        assertThat(deck.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("덱이 모두 소비되면 새로운 카드를 받아온다.")
    void fulfillDeck() {
        Deck deck = DeckFactory.create();
        for (int i = 0; i < 53; i++) {
            deck.dealCard();
        }
        assertThat(deck.isEmpty()).isFalse();
    }
}
