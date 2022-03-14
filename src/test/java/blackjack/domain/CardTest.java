package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    @DisplayName("카드 인스턴스 생성 확인")
    public void createCard() {
        Suit suit = Suit.SPADE;
        Symbol symbol = Symbol.FOUR;
        Card card = new Card(suit, symbol);

        assertThat(card).isInstanceOf(Card.class);
    }
}
