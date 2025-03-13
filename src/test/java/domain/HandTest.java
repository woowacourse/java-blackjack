package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @DisplayName("기존 카드의 합이 21 초과라면 true를 반환한다")
    @Test
    void test1() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.FOUR, CardType.CLOVER),
                new Card(CardNumberType.EIGHT, CardType.DIAMOND),
                new Card(CardNumberType.JACK, CardType.DIAMOND));
        Hand hand = new Hand(testCards);

        //when & then
        assertThat(hand.isBust()).isTrue();
    }

    @DisplayName("추가 배분 시 카드의 합을 구할 때, ACE는 모두 1로 계산하며 예외가 발생하지 않는다")
    @Test
    void test8() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.ACE, CardType.CLOVER),
                new Card(CardNumberType.ACE, CardType.DIAMOND),
                new Card(CardNumberType.ACE, CardType.HEART),
                new Card(CardNumberType.EIGHT, CardType.HEART));
        Hand hand = new Hand(testCards);

        //when
        assertThat(hand.isBust()).isFalse();
    }

    @DisplayName("새로 배분된 카드를 저장한다")
    @Test
    void test2() {
        //given
        Hand hand = Hand.createEmpty();
        Card testCard = new Card(CardNumberType.SIX, CardType.CLOVER);

        //when
        hand.add(testCard);

        //then
        assertThat(hand.getCards()).contains(testCard);
    }


    @DisplayName("카드의 합을 구한다")
    @Test
    void test5() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.SIX, CardType.CLOVER),
                new Card(CardNumberType.JACK, CardType.DIAMOND));
        Hand hand = new Hand(testCards);
        //when
        int sum = hand.calculateSum();
        //then
        assertThat(sum).isEqualTo(16);
    }

    @DisplayName("만약 ACE가 포함된 경우, 나머지 카드의 합이 11 이상이면 ACE를 1로 간주한다")
    @Test
    void test10() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.FIVE, CardType.CLOVER),
                new Card(CardNumberType.SIX, CardType.DIAMOND),
                new Card(CardNumberType.ACE, CardType.DIAMOND)
        );
        Hand hand = new Hand(testCards);
        //when
        int sum = hand.calculateSum();
        //then
        assertThat(sum).isEqualTo(12);
    }

    @DisplayName("만약 ACE가 포함된 경우, 나머지 카드의 합이 10 이하이면 ACE를 11으로 간주한다")
    @Test
    void test6() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.FIVE, CardType.CLOVER),
                new Card(CardNumberType.FIVE, CardType.DIAMOND),
                new Card(CardNumberType.ACE, CardType.DIAMOND)
                );
        Hand hand = new Hand(testCards);
        //when
        int sum = hand.calculateSum();
        //then
        assertThat(sum).isEqualTo(21);
    }

    @DisplayName("ACE가 여러 개인 경우, 각 ACE를 최적의 값으로 포함하여 총합을 계산한다")
    @Test
    void test7() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.ACE, CardType.CLOVER),
                new Card(CardNumberType.ACE, CardType.HEART),
                new Card(CardNumberType.ACE, CardType.DIAMOND)
        );
        Hand hand = new Hand(testCards);
        //when
        int sum = hand.calculateSum();
        //then
        assertThat(sum).isEqualTo(13);
    }

    @DisplayName("카드 묶음의 전체 카드를 추가할 수 있다")
    @Test
    void test13() {
        //given
        List<Card> testCards = List.of(
                new Card(CardNumberType.ACE, CardType.HEART),
                new Card(CardNumberType.ACE, CardType.DIAMOND)
        );
        Hand emptyHand = Hand.createEmpty();

        //when
        emptyHand.addAll(testCards);

        //thens
        assertThat(emptyHand.getCards()).hasSize(2);
        assertThat(emptyHand.getCards()).containsExactlyInAnyOrderElementsOf(testCards);
    }
}
