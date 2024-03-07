package blackjack.domain;

import fixture.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("참가자 소유 카드 테스트")
class HandTest {

    @DisplayName("카드의 합을 계산할 수 있다.")
    @Test
    void testHandSummation() {
        Card card1 = new Card(CardShape.HEART, CardNumber.TWO);
        Card card2 = new Card(CardShape.CLUB, CardNumber.TWO);
        Card card3 = new Card(CardShape.DIAMOND, CardNumber.TWO);

        Hand hand = new Hand(List.of(card1, card2, card3));
        int expected = hand.sum();

        assertThat(expected).isEqualTo(6);
    }

    @DisplayName("특정 카드를 핸드에 추가할 수 있다")
    @Test
    void testAppendCard() {
        Card card1 = new Card(CardShape.HEART, CardNumber.TWO);
        List<Card> cards = new ArrayList<>();

        Hand hand = new Hand(cards);
        hand.append(card1);

        assertThat(hand.getCards()).containsExactly(card1);
    }

    @DisplayName("핸드에 에이스 카드가 몇개 있는지 확인할 수 있다")
    @Test
    void testCountAceInHand() {
        Hand hand = HandFixture.of(1, 1, 1, 3, 4, 5);
        assertThat(hand.countAce()).isEqualTo(3);
    }
}
