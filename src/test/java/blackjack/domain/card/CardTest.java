package blackjack.domain.card;

import static blackjack.CardConstant.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("Card 객체는 52개의 서로 다른 Card를 가지고 있따.")
    void getCards() {
        List<Card> cards = Card.getCards();
        long count = cards.stream()
            .distinct()
            .count();
        assertThat(count).isEqualTo(52);
    }

    @Test
    @DisplayName("CarNumber와 CardShape를 입력 후, Card를 가져온다.")
    void getInstance() {
        Card card = CLOVER_FIVE;
        assertThat(card.getName()).isEqualTo("5");
        assertThat(card.getSymbol()).isEqualTo("♣");
    }

    @Test
    @DisplayName("Card가 Ace카드인지 확인한다.")
    void isAce() {
        assertThat(CLOVER_ACE.isAce()).isTrue();
    }
}