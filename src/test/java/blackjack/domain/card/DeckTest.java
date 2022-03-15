package blackjack.domain.card;

import static blackjack.domain.card.Deck.EMPTY_CARD_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.strategy.RandomCardsGenerateStrategy;
import java.util.List;
import java.util.NoSuchElementException;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck(new RandomCardsGenerateStrategy());
    }

    @Test
    @DisplayName("덱이 정상적으로 생성되었는지 확인")
    void create() {
        assertThat(deck).isNotNull();
    }

    @Test
    @DisplayName("덱이 정상적으로 52장의 카드를 가지고 있는지 확인")
    void createFixedCards() {
        List<Card> cards = deck.getCards();

        assertThat(cards.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("덱이 생성되었을때 정상적으로 카드를 주는지 확인")
    void draw() {
        Card card = deck.draw();

        assertThat(card).isNotNull();
    }

    @Test
    @DisplayName("카드가 모두 소진되었을 때 예외를 발생한다")
    void cardEmptyException() {

        ThrowingCallable throwingCallable = () -> {
            int deckSize = deck.getCards().size();
            for (int i = 0; i < deckSize + 1; i++) {
                deck.draw();
            }
        };

        Assertions.assertThatThrownBy(throwingCallable)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(EMPTY_CARD_EXCEPTION_MESSAGE);
    }
}
