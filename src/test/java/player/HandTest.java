package player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
    void saveCard() {
        hand = new Hand();
        Card card = new Card(CardNumber.ACE, Pattern.CLOVER);
        hand.add(card);

        assertThat(hand.getCards()).contains(card);
    }

    @Test
    @DisplayName("카드들의 점수를 계산할 수 있다.")
    void calculateScore() {
        hand = new Hand();
        Card card = new Card(CardNumber.TWO, Pattern.CLOVER);
        Card card2 = new Card(CardNumber.EIGHT, Pattern.HEART);
        hand.add(card);
        hand.add(card2);

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(10);
    }

    @DisplayName("에이스가 있을 때")
    @Nested
    class AceTest {
        @DisplayName("합계가 11 이하이면 에이스가 11로 계산된다.")
        @Test
        void underEleven() {
            hand = new Hand();
            Card card = new Card(CardNumber.ACE, Pattern.HEART);
            Card card1 = new Card(CardNumber.TWO, Pattern.HEART);
            hand.add(card);
            hand.add(card1);

            int score = hand.calculateScore();
            assertThat(score).isEqualTo(13);
        }
    }

}
