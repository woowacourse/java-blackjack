package domain.card;

import domain.CardsFactory;
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

        CardsFactory cardsFactory = new CardsFactory();
        hand.addCard(cardsFactory.createScore18Cards());

        assertThat(hand.getTotal()).isEqualTo(18);
    }

    @Nested
    @DisplayName("ACE 고유 숫자값 테스트")
    class AceDetectTest{
        @Test
        @DisplayName("Hand 는 현재 카드 패에서 고유 숫자 값이 11인 ACE 가 있는지 판별할 수 있다")
        void test3() {
            Hand hand = new Hand();

            CardsFactory cardsFactory = new CardsFactory();
            hand.addCard(cardsFactory.createScore18CardsWithAce());

            assertThat(hand.containsOriginalAce()).isTrue();
        }

        @Test
        @DisplayName("Hand 는 현재 카드 패에서 고유 숫자 값이 11인 ACE 가 있는지 판별할 수 있다 - 2")
        void test4() {
            Hand hand = new Hand();

            hand.addCard(new Card(Denomination.TWO, Suit.CLUB));

            assertThat(hand.containsOriginalAce()).isFalse();
        }

        @Test
        @DisplayName("Original ACE(11) 가 존재하는 경우 하나의 Original ACE value 를 1로 변경할 수 있다.")
        void test5() {
            Hand hand = new Hand();

            hand.addCard(new Card(Denomination.ACE, Suit.CLUB));

            hand.setOriginalAceValueToOne();

            assertThat(hand.containsOriginalAce()).isFalse();
        }
    }

    @Test
    @DisplayName("Hand 의 총합이 21이 넘으면 버스트이다.")
    void test6() {
        Hand hand = new Hand();

        CardsFactory cardsFactory = new CardsFactory();
        hand.addCard(cardsFactory.createCanResolveBustCardsWithOneAce());

        assertThat(hand.isBust()).isTrue();
    }

    @Nested
    @DisplayName("블랙잭 판별 테스트")
    class HandBlackJackTest{
        @Test
        @DisplayName("Hand에 카드가 2장이 있으면서, 총 합이 21이면 블랙잭이다.")
        void test7() {
            Hand hand = new Hand();

            CardsFactory cardsFactory = new CardsFactory();
            hand.addCard(cardsFactory.createBlackJackCards1());

            assertThat(hand.isBlackJack()).isTrue();
        }

        @Test
        @DisplayName("Hand에 카드가 2장이 있으면서, 총 합이 21이 아니면 블랙잭이 아니다.")
        void test8() {
            Hand hand = new Hand();

            CardsFactory cardsFactory = new CardsFactory();
            hand.addCard(cardsFactory.createScore19TwoCards());

            assertThat(hand.isBlackJack()).isFalse();
        }

        @Test
        @DisplayName("Hand에 카드가 3장이면서, 총 합이 21이면 블랙잭이 아니다.")
        void test9() {
            Hand hand = new Hand();

            CardsFactory cardsFactory = new CardsFactory();
            hand.addCard(cardsFactory.createMaxScoreCards());

            assertThat(hand.isBlackJack()).isFalse();
        }
    }

    @Test
    @DisplayName("Hand에 21이면 최대값이다.")
    void test7() {
        Hand hand = new Hand();

        CardsFactory cardsFactory = new CardsFactory();
        hand.addCard(cardsFactory.createMaxScoreCards());

        assertThat(hand.isMaxScore()).isTrue();
    }
}
