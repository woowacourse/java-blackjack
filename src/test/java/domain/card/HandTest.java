package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static domain.card.Denomination.ACE;
import static org.assertj.core.api.Assertions.assertThat;

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
    @Nested
    @DisplayName("ACE 고유 숫자값 테스트")
    class AceDetectTest{
        @Test
        @DisplayName("Hand 는 현재 카드 패에서 고유 숫자 값이 11인 ACE 가 있는지 판별할 수 있다")
        void test3() {
            Hand hand = new Hand();

            hand.addCard(new Card(Denomination.TWO, Suit.CLUB));
            hand.addCard(new Card(Denomination.THREE, Suit.CLUB));
            hand.addCard(new Card(Denomination.ACE, Suit.CLUB));

            assertThat(hand.containsOriginalAce()).isTrue();
        }

        @Test
        @DisplayName("Hand 는 현재 카드 패에서 고유 숫자 값이 11인 ACE 가 있는지 판별할 수 있다 - 2")
        void test4() {
            Hand hand = new Hand();

            hand.addCard(new Card(Denomination.TWO, Suit.CLUB));
            hand.addCard(new Card(Denomination.THREE, Suit.CLUB));

            assertThat(hand.containsOriginalAce()).isFalse();
        }

        @Test
        @DisplayName("Original ACE(11) 가 존재하는 경우 하나의 Original ACE value 를 1로 변경할 수 있다.")
        void test5() {
            Hand hand = new Hand();

            hand.addCard(new Card(Denomination.TWO, Suit.CLUB));
            hand.addCard(new Card(Denomination.ACE, Suit.CLUB));

            hand.setOriginalAceValueToOne();

            assertThat(hand.containsOriginalAce()).isFalse();
        }
    }

    @Test
    @DisplayName("Hand 의 총합이 21이 넘으면 버스트이다.")
    void test6() {
        Hand hand = new Hand();

        hand.addCard(new Card(Denomination.TWO, Suit.CLUB));
        hand.addCard(new Card(Denomination.ACE, Suit.CLUB));

        assertThat(hand.isBust()).isFalse();
    }

}
