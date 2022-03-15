package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static utils.ExceptionMessages.CAN_NOT_POP_CARD_ERROR;

import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void init() {
        deck = Deck.getInstance();
    }

    @Test
    @DisplayName("초기 카드 개수는 52장이다.")
    void checkDeckInitialSize() {
        assertThat(deck.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("카드 뽑기 확인")
    void testHandout() {
        assertAll(
            () -> assertThat(deck.handOut()).isInstanceOf(Card.class),
            () -> assertThat(deck.size()).isEqualTo(51)
        );
    }

    @Test
    @DisplayName("첫턴 뽑기 카드 확인")
    void testHandOutInitialTurn() {
        assertAll(
            () -> assertThat(deck.handOutInitialTurn().size()).isEqualTo(2),
            () -> assertThat(deck.size()).isEqualTo(50)
        );
    }

    @Test
    @DisplayName("카드가 없을 때 에러가 발생한다.")
    void drawCardExceptionWhenNoCard() {
        IntStream.range(0, 52)
            .forEach(index -> deck.handOut());

        assertThatThrownBy(() -> deck.handOut())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage(CAN_NOT_POP_CARD_ERROR);
    }
}