package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    private static final int INIT_CARD_DECK_SIZE = Shape.values().length * Symbol.values().length;

    @Test
    @DisplayName("카드덱 생성 시, 카드덱은 총 52장의 카드로 이루어져있는 지 테스트")
    public void cardDeckSize() {
        CardDeck cardDeck = new CardDeck();
        int cardDeckSize = Shape.values().length * Symbol.values().length;
        assertThat(cardDeck.size()).isEqualTo(cardDeckSize);
    }

    @DisplayName("카드 덱에서 카드 1장을 뽑는다.")
    @Test
    void draw() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.draw();
        assertThat(cardDeck.size()).isEqualTo(INIT_CARD_DECK_SIZE - 1);
    }

    @DisplayName("카드 덱에서 기본 카드 2장을 뽑는다.")
    @Test
    void drawDefaultCards() {
        CardDeck cardDeck = new CardDeck();
        Cards cards = cardDeck.drawDefaultCards();
        assertThat(cards.getCards()
                        .size()).isEqualTo(2);
    }
}
