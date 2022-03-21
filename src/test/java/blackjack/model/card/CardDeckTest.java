package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {
    private static final int CARD_COUNT_IN_DECK = 52;

    @DisplayName("CardDeck 인스턴스가 생성되는지 확인한다.")
    @Test
    void createDeck() {
        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck).isInstanceOf(CardDeck.class);
    }

    @DisplayName("CardDeck의 52장의 카드를 모두 분배하여 남은 카드가 없으면 오류를 발생한다.")
    @Test
    void draw_exception_empty() {
        CardDeck cardDeck = new CardDeck();

        for (int i = 0; i < CARD_COUNT_IN_DECK; i++) {
            cardDeck.draw();
        }

        assertThatThrownBy(cardDeck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드 덱에 남은 카드가 없어 카드를 받을수 없습니다.");
    }
}