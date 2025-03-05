package domain;

import static domain.Denomination.ACE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    @DisplayName("Hand 에 카드를 받아 추가한다.")
    void test1() {
        Hand hand = new Hand();

        Card card = new Card(ACE, Suit.CLUB);
        hand.addCard(card);

        assertThat(hand.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Hand 는 현재 카드패의 고유 숫자값의 총합을 계산할 수 있다.")
    void test2() {
        Hand hand = new Hand();

        hand.addCard(new Card(Denomination.TWO, Suit.CLUB));    // 2
        hand.addCard(new Card(Denomination.THREE, Suit.CLUB));  // 3
        hand.addCard(new Card(Denomination.JACK, Suit.CLUB));   // 10

        assertThat(hand.getTotal()).isEqualTo(15);
    }

    @Test
    @DisplayName("Hand 는 현재 카드 패에서 ACE 를 판별할 수 있다")
    void test3() {
        Hand hand = new Hand();

        hand.addCard(new Card(Denomination.TWO, Suit.CLUB));
        hand.addCard(new Card(Denomination.THREE, Suit.CLUB));
        hand.addCard(new Ace(Suit.CLUB));

        assertThat(hand.containsAce()).isTrue();
    }
}
