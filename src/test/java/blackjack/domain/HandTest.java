package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.common.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {
    @DisplayName("Hand 생성 시에 카드는 두 장을 넣을 수 있다")
    @Test
    void test1() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);

        // when & then
        assertThatCode(() -> Hand.of(card1, card2)).doesNotThrowAnyException();
    }

    @DisplayName("카드를 추가한다")
    @Test
    void test2() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = Hand.of(card1, card2);

        Card newCard = new Card(CardSuit.SPADE, CardRank.KING);

        // when
        hand.hit(newCard);

        // then
        assertThat(hand.getAllCards()).containsExactly(card1, card2, newCard);
    }

    @DisplayName("모든 카드를 반환한다.")
    @Test
    void test5() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = Hand.of(card1, card2);

        // when
        List<Card> cards = hand.getAllCards();

        // then
        assertThat(cards).containsExactly(card1, card2);
    }

    @DisplayName("Hand 생성 시에 card 가 null 이 존재하면 예외를 던진다.")
    @Test
    void test6() {
        // give & when & then
        assertThatThrownBy(() -> Hand.of(null, null)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("원하는 인덱스의 Card를 가져온다.")
    @Test
    void test7() {
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = Hand.of(card1, card2);

        Card result = hand.getCard(0);

        assertThat(result).isEqualTo(card1);
    }

    @DisplayName("해당 인덱스의 Card가 없다면 예외를 반환한다.")
    @Test
    void test8() {
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = Hand.of(card1, card2);

        assertThatThrownBy(() -> hand.getCard(2)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_CARD_INDEX.getMessage());
    }

    @DisplayName("최적의 값을 반환한다.")
    @Test
    void test9() {
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);

        Hand hand = Hand.of(card1, card2);

        assertThat(hand.getBestCardValue()).isEqualTo(16);
    }
}
