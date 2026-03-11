package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.participant.Hand;
import blackjack.domain.trump.Card;
import blackjack.domain.trump.Denomination;
import blackjack.domain.trump.Suit;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

    @Nested
    @DisplayName("블랙잭 판정 테스트")
    class 블랙잭_판정_테스트 {

        @Test
        @DisplayName("정상 테스트")
        void 정상_테스트() {
            List<Card> cards = List.of(
                new Card(Suit.DIAMOND, Denomination.KING),
                new Card(Suit.HEART, Denomination.ACE));
            Hand hand = new Hand(cards);
            boolean expected = true;

            boolean actual = hand.isBlackjack();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("카드가 2장이고 카드 점수 합이 21이 아닌 경우")
        void 카드가_2장이고_카드_점수_합이_21이_아닌_경우() {
            List<Card> cards = List.of(
                new Card(Suit.DIAMOND, Denomination.TWO),
                new Card(Suit.HEART, Denomination.ACE));
            Hand hand = new Hand(cards);
            boolean expected = false;

            boolean actual = hand.isBlackjack();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("카드가 3장이고 카드 점수 합이 21인 경우")
        void 카드가_3장이고_카드_점수_합이_21인_경우() {
            List<Card> cards = List.of(
                new Card(Suit.DIAMOND, Denomination.TWO),
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.HEART, Denomination.EIGHT));
            Hand hand = new Hand(cards);
            boolean expected = false;

            boolean actual = hand.isBlackjack();

            assertThat(actual).isEqualTo(expected);
        }
    }
}
