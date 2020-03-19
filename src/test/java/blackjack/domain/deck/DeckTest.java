package blackjack.domain.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {

    @Test
    @DisplayName("Deck 생성시 null이 입력되면 예외 발생")
    void deckNullException() {
        assertThatThrownBy(() -> new Deck(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Deck 생성시 비어있는 카드가 입력되면 예외 발생")
    void deckEmptyException() {
        assertThatThrownBy(() -> new Deck(new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("카드 꺼내기")
    void pick() {
        List<Card> cards = CardFactory.generate();
        Deck deck = new Deck(cards);
        assertThat(deck.pick()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("모든 카드를 소진시에 예외 발생")
    void pickThrowException() {
        List<Card> cards = CardFactory.generate();
        Deck deck = new Deck(cards);
        for (int i = 0; i < 52; i++) {
            deck.pick();
        }
        assertThatThrownBy(() -> deck.pick())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드를 모두 사용하셨습니다.");
    }
}