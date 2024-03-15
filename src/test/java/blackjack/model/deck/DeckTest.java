package blackjack.model.deck;

import static blackjack.model.deck.Deck.CAN_NOT_DRAWN_CARD;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("덱을 생성하지 않고 덱에서 카드를 빼면 예외가 발생한다.")
    void throwErrorWhenConsumeCardBeforeMakeDeck() {
        Deck deck = Fixtures.createDeck();

        assertThatThrownBy(deck::drawn)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(CAN_NOT_DRAWN_CARD);
    }

    @Test
    @DisplayName("카드 덱(52장)을 전부 소진하면 예외가 발생한다.")
    void throwErrorWhenConsumeAllCards() {
        Deck deck = Fixtures.createDeck();
        deck.makeDeck();

        assertThatThrownBy(repeatDistribute(deck::drawn, 53))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(CAN_NOT_DRAWN_CARD);
    }

    static ThrowingCallable repeatDistribute(Runnable distributor, int repeatCount) {
        return () -> {
            IntStream.rangeClosed(1, repeatCount)
                    .forEach(ignore -> distributor.run());
        };
    }

    static class Fixtures {
        static Deck createDeck() {
            return new Deck();
        }
    }
}
