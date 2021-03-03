package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("카드덱 생성 시, 카드덱은 총 52장의 카드로 이루어져있는 지 테스트")
    public void cardDeckSize() {
        CardDeck cardDeck = new CardDeck();
        assertThat(cardDeck.size()).isEqualTo(52);
    }

    @DisplayName("카드 덱에서 카드 1장을 뽑는다.")
    @Test
    void draw() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.draw();
        assertThat(cardDeck.size()).isEqualTo(51);
    }

    @DisplayName("카드 덱에서 기본 카드 2장을 뽑는다.")
    @Test
    void drawDefaultCards() {
        CardDeck cardDeck = new CardDeck();
        Cards cards = cardDeck.drawDefaultCards();
        assertThat(cards.size()).isEqualTo(2);
    }
}
