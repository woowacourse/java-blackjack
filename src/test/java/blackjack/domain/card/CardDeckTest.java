package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardDeckTest {
    @DisplayName("52장의 카드로 이루어진 덱이 생성되었는지 테스트한다.")
    @Test
    void deckCreationTest() {
        CardDeck cardDeck = CardDeck.createDeck();
        assertThat(cardDeck.getDeck()).hasSize(52);
    }

    @DisplayName("카드 드로우 기능 확인")
    @Test
    void cardDraw() {
        CardDeck cardDeck = CardDeck.createDeck();
        cardDeck.drawCard();

        assertThat(cardDeck.getDeck()).hasSize(51);
    }

    @DisplayName("카드를 모두 뽑았을경우 에러발생 확인")
    @Test
    void noCardCheck() {
        CardDeck cardDeck = CardDeck.createDeck();

        for (int i = 0; i < 52; i++) {
            cardDeck.drawCard();
        }

        assertThatThrownBy(() -> {
            cardDeck.drawCard();
        }).isInstanceOf(NoSuchElementException.class);
    }
}