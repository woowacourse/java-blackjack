package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    private void pollCards(final CardDeck cardDeck, final int pollCount) {
        for (int i = 0; i < pollCount; i++) {
            cardDeck.provideCard();
        }
    }

    @Test
    @DisplayName("초기 카드 2장 제공시, 남은 카드가 2장 보다 적으면 예외를 발생시킨다.")
    void provideInitCardsException() {
        final CardDeck cardDeck = CardDeck.init();
        pollCards(cardDeck, 51);
        assertThatThrownBy(() -> cardDeck.provideInitCards())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("남은 카드가 2장 미만입니다.");
    }

    @Test
    @DisplayName("초기 카드 2장을 반환할 수 있다.")
    void provideInitCards() {
        final CardDeck cardDeck = CardDeck.init();
        assertThat(cardDeck.provideInitCards()).hasSize(2);
    }

    @Test
    @DisplayName("카드 1장 제공시, 남은 카드가 없으면 예외를 발생시킨다.")
    void provideException() {
        final CardDeck cardDeck = CardDeck.init();
        pollCards(cardDeck, 52);
        assertThatThrownBy(() -> cardDeck.provideCard())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("남은 카드가 없습니다.");
    }

    @Test
    @DisplayName("카드 1장을 반환할 수 있다.")
    void provideCard() {
        final CardDeck cardDeck = CardDeck.init();
        assertThat(cardDeck.provideCard()).isInstanceOf(Card.class);
    }
}