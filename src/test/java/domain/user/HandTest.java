package domain.user;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class HandTest {

    @Test
    @DisplayName("카드 풀에 카드가 저장된다")
    void makeHandTest() {
        List<Card> cards = List.of(
                Card.of(Suit.CLOVER, Denomination.EIGHT),
                Card.of(Suit.DIAMOND, Denomination.ACE),
                Card.of(Suit.HEART, Denomination.KING)
        );

        Hand hand = new Hand(cards);

        assertThat(hand.getCards())
                .isEqualTo(cards);
    }

    @Test
    @DisplayName("카드가 없을때에는 합이 0이다")
    void sumHandWhenCardsNotExist() {
        List<Card> cards = Collections.emptyList();

        Hand hand = new Hand(cards);

        assertThat(hand.calculateScore())
                .isEqualTo(0);
    }

    @Test
    @DisplayName("카드가 있을때에는 합이 카드 값에 따라 결정된다")
    void sumHandWhenCardsExist() {
        List<Card> cards = List.of(
                Card.of(Suit.CLOVER, Denomination.FIVE),
                Card.of(Suit.HEART, Denomination.EIGHT)
        );

        Hand hand = new Hand(cards);

        assertThat(hand.calculateScore())
                .isEqualTo(13);
    }

    @Test
    @DisplayName("에이스카드는 나머지 카드의 합이 10보다 크면 1로 결정된다")
    void decideAceSumOver() {
        List<Card> cards = List.of(
                Card.of(Suit.CLOVER, Denomination.FIVE),
                Card.of(Suit.HEART, Denomination.EIGHT),
                Card.of(Suit.CLOVER, Denomination.ACE)
        );

        Hand hand = new Hand(cards);

        assertThat(hand.calculateScore())
                .isEqualTo(14);
    }

    @Test
    @DisplayName("에이스카드가 먼저 뽑혀도 나머지 카드의 합이 10보다 크면 1로 결정된다")
    void decideAceSumOverWhenFirstDraw() {
        List<Card> cards = List.of(
                Card.of(Suit.CLOVER, Denomination.ACE),
                Card.of(Suit.CLOVER, Denomination.FIVE),
                Card.of(Suit.HEART, Denomination.EIGHT)

        );

        Hand hand = new Hand(cards);

        assertThat(hand.calculateScore())
                .isEqualTo(14);
    }

    @Test
    @DisplayName("에이스카드는 나머지 카드의 합이 10보다 작으면 11로 결정된다")
    void decideAceSumUnder() {
        List<Card> cards = List.of(
                Card.of(Suit.CLOVER, Denomination.FIVE),
                Card.of(Suit.HEART, Denomination.FOUR),
                Card.of(Suit.CLOVER, Denomination.ACE)
        );

        Hand hand = new Hand(cards);

        assertThat(hand.calculateScore())
                .isEqualTo(20);
    }

    @Test
    @DisplayName("에이스가 2장 이상일 때 카드의 합은 21을 넘지 않는 가장 큰 수로 결정된다.")
    void decideDoubleAceSumUnder() {
        List<Card> cards = List.of(
                Card.of(Suit.CLOVER, Denomination.ACE),
                Card.of(Suit.SPADE, Denomination.ACE),
                Card.of(Suit.CLOVER, Denomination.FOUR),
                Card.of(Suit.HEART, Denomination.FOUR)
        );

        Hand hand = new Hand(cards);

        assertThat(hand.calculateScore())
                .isEqualTo(20);
    }

    @Test
    @DisplayName("카드가 잘 들어가는지 테스트한다.")
    void addTest() {
        Hand hand = new Hand(Collections.emptyList());
        Card card = Card.of(Suit.CLOVER, Denomination.FOUR);

        hand.add(card);

        assertThat(hand.getCards()).contains(card);
    }

    @Test
    void isBust() {
        Hand hand = new Hand(List.of(Card.of(Suit.DIAMOND, Denomination.TEN),
                Card.of(Suit.CLOVER, Denomination.TEN)));

        hand.add(Card.of(Suit.DIAMOND, Denomination.FIVE));

        assertThat(hand.isBust()).isTrue();

    }
}
