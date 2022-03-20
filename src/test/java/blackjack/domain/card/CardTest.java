package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드가 에이스인지 확인")
    void isAce() {
        assertThat(new Card(Suit.DIAMOND, Denomination.ACE).isAce()).isTrue();
    }

    @Test
    @DisplayName("카드의 포인트 얻기")
    void getCardPoint() {
        Card card = new Card(Suit.CLOVER, Denomination.JACK);

        assertThat(card.getPoint()).isEqualTo(10);
    }
}
