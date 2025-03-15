package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import card.CardNumberType;
import card.CardShapeType;
import card.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import participant.Dealer;

public class DealerTest {
    @DisplayName("카드의 합계가 16 이하이면 True를 반환한다")
    @Test
    void test3() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.SIX, CardShapeType.CLOVER),
                new Card(CardNumberType.JACK, CardShapeType.DIAMOND));
        Hand hand = new Hand(testCards);
        Dealer dealer = Dealer.createWithNoHand();
        Dealer dealerWithHands = (Dealer) dealer.initializeHandWith(hand);
        //when
        boolean isPossibleDraw = dealerWithHands.shouldDrawCard();

        //then
        assertThat(isPossibleDraw).isTrue();
    }

    @DisplayName("카드의 합계가 16 초과이면 False를 반환한다")
    @Test
    void test4() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.SIX, CardShapeType.CLOVER),
                new Card(CardNumberType.ACE, CardShapeType.DIAMOND),
                new Card(CardNumberType.JACK, CardShapeType.DIAMOND));
        Hand hand = new Hand(testCards);
        Dealer dealer = Dealer.createWithNoHand();
        Dealer dealerWithHands = (Dealer) dealer.initializeHandWith(hand);

        //when
        boolean isPossibleDraw = dealerWithHands.shouldDrawCard();

        //then
        assertThat(isPossibleDraw).isFalse();
    }

    @DisplayName("딜러의 카드 묶음이 ACE를 포함하지 않을 경우 단순 합으로 계산한다")
    @Test
    void test9() {
        //given
        List<Card> testCards = List.of(
                new Card(CardNumberType.QUEEN, CardShapeType.HEART),
                new Card(CardNumberType.SIX, CardShapeType.DIAMOND)
        );
        Hand hand = new Hand(testCards);
        Dealer dealer = Dealer.createWithNoHand();
        Dealer dealerWithHands = (Dealer) dealer.initializeHandWith(hand);

        //when
        boolean isPossibleDraw = dealerWithHands.shouldDrawCard();

        //then
        assertThat(isPossibleDraw).isTrue();
    }

    @DisplayName("딜러의 카드 묶음이 ACE를 한 장 포함할 경우 최댓값을 합으로 간주한다")
    @Test
    void test11() {
        //given
        List<Card> testCards = List.of(
                new Card(CardNumberType.QUEEN, CardShapeType.HEART),
                new Card(CardNumberType.ACE, CardShapeType.DIAMOND)
        );
        Hand hand = new Hand(testCards);
        Dealer dealer = Dealer.createWithNoHand();
        Dealer dealerWithHands = (Dealer) dealer.initializeHandWith(hand);

        //when
        boolean isPossibleDraw = dealerWithHands.shouldDrawCard();

        //then
        assertThat(isPossibleDraw).isFalse();
    }

    @DisplayName("딜러의 카드 묶음이 ACE를 두 장 가질 경우 추가 카드 한장을 받는다")
    @Test
    void test12() {
        //given
        List<Card> testCards = List.of(
                new Card(CardNumberType.ACE, CardShapeType.HEART),
                new Card(CardNumberType.ACE, CardShapeType.DIAMOND)
        );
        Hand hand = new Hand(testCards);
        Dealer dealer = Dealer.createWithNoHand();
        Dealer dealerWithHands = (Dealer) dealer.initializeHandWith(hand);

        //when
        boolean isPossibleDraw = dealerWithHands.shouldDrawCard();

        //then
        assertThat(isPossibleDraw).isTrue();
    }
}
