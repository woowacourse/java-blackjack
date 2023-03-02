package player;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import card.Card;
import card.CardNumber;
import card.Pattern;

class DealerTest {
    @Test
    @DisplayName("딜러를 생성한다.")
    void create() {
        assertThatCode(() -> new Dealer())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러는 카드를 받을 수 있다")
    void hit() {
        Dealer dealer = new Dealer();
        Card card = new Card(CardNumber.ACE, Pattern.HEART);

        assertThatCode(() -> dealer.hit(card))
                .doesNotThrowAnyException();
    }
}
