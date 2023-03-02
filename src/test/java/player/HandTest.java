package player;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import card.Card;
import card.CardNumber;
import card.Pattern;

class HandTest {
    Hand hand;

    @Test
    @DisplayName("생성자 테스트")
    void create() {
        assertThatCode(Hand::new)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Hand는 카드를 받을 수 있다.")
    void add() {
        hand = new Hand();

        assertThatCode(() -> hand.add(new Card(CardNumber.ACE, Pattern.CLOVER)))
                .doesNotThrowAnyException();
    }
}
