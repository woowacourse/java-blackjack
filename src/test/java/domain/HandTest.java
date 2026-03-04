package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    @DisplayName("점수 합계 테스트")
    void 정상_테스트_1() {
        List<Card> cards = List.of(Card.JACK, Card.TEN);
        Hand hand = new Hand(cards);

        int expected = 20;
        int actual = hand.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("버스트 판정 테스트: 점수 합계가 21 초과인 경우")
    void 정상_테스트_2() {
        List<Card> cards = List.of(Card.JACK, Card.TEN, Card.FIVE);
        Hand hand = new Hand(cards);
        boolean expected = true;

        boolean actual = hand.isBurst();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("버스트 판정 테스트: 점수 합계가 21 이하인 경우")
    void 정상_테스트_3() {
        List<Card> cards = List.of(Card.JACK, Card.NINE, Card.TWO);
        Hand hand = new Hand(cards);
        boolean expected = false;

        boolean actual = hand.isBurst();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("ACE 처리 테스트: 버스트가 되지 않은 경우")
    void 정상_테스트_4() {
        List<Card> cards = List.of(Card.NINE, Card.ACE);
        Hand hand = new Hand(cards);
        int expected = 20;

        int actual = hand.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("ACE 처리 테스트: 버스트가 된 경우")
    void 정상_테스트_5() {
        List<Card> cards = List.of(Card.ACE, Card.ACE);
        Hand hand = new Hand(cards);
        int expected = 12;

        int actual = hand.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }
}
