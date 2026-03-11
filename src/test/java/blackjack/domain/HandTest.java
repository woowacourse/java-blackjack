package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    @DisplayName("점수 합계 테스트")
    void 점수_합계_테스트() {
        List<Card> cards = List.of(
            new Card(Suit.DIAMOND, Denomination.JACK),
            new Card(Suit.DIAMOND, Denomination.TEN)
        );
        Hand hand = new Hand(new ArrayList<>());
        cards.forEach(hand::add);

        int expected = 20;
        int actual = hand.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("버스트 판정 테스트: 점수 합계가 21 초과인 경우")
    void 버스트_판정_테스트_점수_합계가_21_초과인_경우() {
        List<Card> cards = List.of(
            new Card(Suit.DIAMOND, Denomination.JACK),
            new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.DIAMOND, Denomination.FIVE));
        Hand hand = new Hand(new ArrayList<>());
        cards.forEach(hand::add);
        boolean expected = true;

        boolean actual = hand.isBurst();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("버스트 판정 테스트: 점수 합계가 21 이하인 경우")
    void 버스트_판정_테스트_점수_합계가_21_이하인_경우() {
        List<Card> cards = List.of(
            new Card(Suit.DIAMOND, Denomination.JACK),
            new Card(Suit.DIAMOND, Denomination.NINE),
            new Card(Suit.DIAMOND, Denomination.TWO));
        Hand hand = new Hand(new ArrayList<>());
        cards.forEach(hand::add);
        boolean expected = false;

        boolean actual = hand.isBurst();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("ACE 처리 테스트: 버스트가 되지 않은 경우")
    void ACE_처리_테스트_버스트가_되지_않은_경우() {
        List<Card> cards = List.of(
            new Card(Suit.DIAMOND, Denomination.NINE),
            new Card(Suit.DIAMOND, Denomination.ACE));
        Hand hand = new Hand(new ArrayList<>());
        cards.forEach(hand::add);
        int expected = 20;

        int actual = hand.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("ACE 처리 테스트: 버스트가 된 경우")
    void ACE_처리_테스트_버스트가_된_경우() {
        List<Card> cards = List.of(
            new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.ACE));
        Hand hand = new Hand(new ArrayList<>());
        cards.forEach(hand::add);
        int expected = 12;

        int actual = hand.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }
}
