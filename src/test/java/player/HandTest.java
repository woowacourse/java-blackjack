package player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;

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

    @Test
    @DisplayName("Hand에 들어있는 카드들을 가져올 수 있다.")
    void getCards() {
        hand = new Hand();

        assertThat(hand.getCards()).isInstanceOf(ArrayList.class);
    }

    @Test
    @DisplayName("Hand에 추가한 카드가 카드리스트에 들어있다.")
    void getCa1rds() {
        hand = new Hand();
        Card card = new Card(CardNumber.ACE, Pattern.CLOVER);
        hand.add(card);

        assertThat(hand.getCards()).contains(card);
    }
}
