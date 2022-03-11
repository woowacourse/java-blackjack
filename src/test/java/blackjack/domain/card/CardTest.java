package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    @DisplayName("52개의 카드를 미리 Caching 한 후, 가져온다.")
    void caching() {
        List<Card> card = Card.getCards();
        assertThat(card.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("CarNumber와 CardShape를 입력 후, Card를 가져온다.")
    void getInstance() {
        Card card = Card.getInstance(CardShape.CLOVER, CardNumber.FIVE);
        assertThat(card.getName()).isEqualTo("5");
        assertThat(card.getShape()).isEqualTo("클로버");
    }

    @Test
    @DisplayName("Card가 Ace카드인지 확인한다.")
    void isAce() {
        Card card = Card.getInstance(CardShape.CLOVER, CardNumber.ACE);
        assertThat(card.isAce()).isTrue();
    }
}