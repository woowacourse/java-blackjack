package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import domain.gamer.Dealer;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    Deque<Card> cards;
    Dealer dealer;

    @BeforeEach
    void init() {
        Card card1 = new Card(Symbol.HEART, Rank.KING);
        Card card2 = new Card(Symbol.SPADE, Rank.QUEEN);
        Card card3 = new Card(Symbol.SPADE, Rank.THREE);

        cards = Stream.of(card1, card2, card3).collect(Collectors.toCollection(ArrayDeque::new));
        dealer = new Dealer();
    }

    @DisplayName("딜러가 카드를 뽑아서 저장한다.")
    @Test
    void hitTest() {
        // when
        dealer.hit(cards.removeLast());

        // then
        assertThat(dealer.getHand()).hasSize(1);
    }

    @DisplayName("딜러가 가진 카드의 점수를 알 수 있다.")
    @Test
    void calculateTotalScoreTest() {
        // given
        dealer.hit(cards.removeLast());
        dealer.hit(cards.removeLast());
        int expectedScore = 13;

        // when
        int result = dealer.calculateTotalScore();

        // then
        assertThat(result).isEqualTo(expectedScore);
    }

    @DisplayName("딜러가 버스트인지 확인한다.")
    @Test
    void isBust() {
        // given
        dealer.hit(cards.removeLast());
        dealer.hit(cards.removeLast());
        dealer.hit(cards.removeLast());

        // when
        boolean bust = dealer.isBust();

        // then
        assertThat(bust).isTrue();
    }

    @DisplayName("딜러가 카드를 더 받는다.")
    @Test
    void isNotStayTest() {
        // given
        dealer.hit(cards.removeLast());
        dealer.hit(cards.removeLast());

        // when
        boolean stay = dealer.isStay();

        // then
        assertThat(stay).isFalse();
    }

    @DisplayName("딜러가 카드를 더 받지 않는다.")
    @Test
    void isStayTest() {
        // given
        dealer.hit(cards.removeLast());
        dealer.hit(cards.removeLast());
        dealer.hit(cards.removeLast());

        // when
        boolean stay = dealer.isStay();

        // then
        assertThat(stay).isTrue();
    }
}
