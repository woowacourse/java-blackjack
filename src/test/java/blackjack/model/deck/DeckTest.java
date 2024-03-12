package blackjack.model.deck;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    private Deck deck;

    @BeforeEach
    void init() {
        deck = new Deck();
        deck.makeDeck();
    }

    @Test
    @DisplayName("카드 한 세트(52장)을 다 소진할 경우 예외가 발생한다.")
    void throwErrorWhenNoCard() {
        assertThatThrownBy(repeatDistribute(() -> deck.distribute(), 53))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("카드가 부족합니다.");
    }

    static ThrowingCallable repeatDistribute(Runnable distributor, int repeatCount) {
        return () -> {
            IntStream.rangeClosed(1, repeatCount)
                    .forEach(ignore -> distributor.run());
        };
    }
}
