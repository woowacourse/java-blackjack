package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    @DisplayName("초기 카드 2장 제공시, 남은 카드가 2장 보다 적으면 예외를 발생시킨다.")
    void provideInitCardsException() {
        final CardDeck cardDeck = new CardDeck(new ArrayList<>());
        assertThatThrownBy(() -> cardDeck.provideInitCards())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[Error] 남은 카드가 2장 미만입니다.");
    }

    @Test
    @DisplayName("카드 1장 제공시, 남은 카드가 없으면 예외를 발생시킨다.")
    void provideException() {
        final CardDeck cardDeck = new CardDeck(new ArrayList<>());
        assertThatThrownBy(() -> cardDeck.provideCard())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[Error] 남은 카드가 없습니다.");
    }
}