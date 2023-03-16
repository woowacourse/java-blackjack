package blackjack.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Pattern;
import blackjack.domain.participant.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {
    private Hand hand;

    @Test
    @DisplayName("생성자 테스트")
    void create() {
        assertThatCode(Hand::new)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Hand는 카드를 받을 수 있다.")
    void add() {
        hand = new Hand();

        assertThatCode(() -> hand.add(new Card(CardNumber.ACE, Pattern.CLOVER)))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드를 받으면 새로운 hand 객체가 생성된다.")
    @Test
    void addThenCreate() {
        Hand hand = new Hand();
        Hand newHand = hand.add(new Card(CardNumber.KING, Pattern.DIAMOND));
        assertThat(hand).isNotSameAs(newHand);
    }

    @DisplayName("카드를 받으면 새로운 카드가 포함된다.")
    @Test
    void addThenContains() {
        Hand hand = new Hand();
        Card newCard = new Card(CardNumber.KING, Pattern.DIAMOND);
        Hand newHand = hand.add(newCard);
        assertThat(newHand.getCards()).contains(newCard);
    }

    @DisplayName("카드를 여러장 받을 수 있다.")
    @Test
    void addCards() {
        Hand hand = new Hand();
        Card card1 = new Card(CardNumber.KING, Pattern.DIAMOND);
        Card card2 = new Card(CardNumber.TWO, Pattern.CLOVER);
        Card card3 = new Card(CardNumber.EIGHT, Pattern.HEART);
        Hand newHand = hand.add(card1, card2, card3);

        assertThat(newHand.getCards()).contains(card1, card2, card3);
    }

    @Test
    @DisplayName("Hand에 들어있는 카드들을 가져올 수 있다.")
    void getCards() {
        Card card = new Card(CardNumber.KING, Pattern.DIAMOND);
        hand = new Hand(List.of(card));

        assertThat(hand.getCards()).contains(card);
    }

    @Test
    @DisplayName("Hand에 추가한 카드가 카드리스트에 들어있다.")
    void saveCard() {
        hand = new Hand();
        Card card = new Card(CardNumber.ACE, Pattern.CLOVER);
        Hand added = hand.add(card);

        assertThat(added.getCards()).contains(card);
    }

    @Test
    @DisplayName("카드들의 점수를 계산할 수 있다.")
    void calculateScore() {
        hand = new Hand();
        Card card = new Card(CardNumber.TWO, Pattern.CLOVER);
        Card card2 = new Card(CardNumber.EIGHT, Pattern.HEART);

        Hand newHand = hand.add(card, card2);

        Score score = newHand.calculateScore();

        assertThat(score).isEqualTo(new Score(10));
    }

    @Test
    @DisplayName("첫번째 카드를 가져올 수 있다")
    void pickFirstCard() {
        hand = new Hand();
        Card card = new Card(CardNumber.ACE, Pattern.HEART);
        Card card1 = new Card(CardNumber.TEN, Pattern.HEART);
        Card card2 = new Card(CardNumber.FIVE, Pattern.HEART);
        Hand newHand = hand.add(card, card1, card2);

        Card card3 = newHand.pickFirstCard();

        assertThat(card3).isEqualTo(card);
    }

    @Test
    @DisplayName("카드 리스트를 가져올 때 수정이 불가능하다.")
    void cannotModifyCardList() {
        Hand hand = new Hand();
        List<Card> cards = hand.getCards();
        assertThatThrownBy(() -> cards.add(new Card(CardNumber.KING, Pattern.DIAMOND)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("에이스가 있을 때")
    @Nested
    class AceTest {

        @DisplayName("합계가 11 이하이면 에이스가 11로 계산된다.")
        @Test
        void underEleven() {
            hand = new Hand();
            Card card = new Card(CardNumber.ACE, Pattern.HEART);
            Card card1 = new Card(CardNumber.TWO, Pattern.HEART);
            Hand newHand = hand.add(card, card1);

            Score score = newHand.calculateScore();
            assertThat(score).isEqualTo(new Score(13));
        }

        @DisplayName("합계가 11 초과이면 에이스가 1로 계산된다.")
        @Test
        void upperEleven() {
            hand = new Hand();
            Card card = new Card(CardNumber.ACE, Pattern.HEART);
            Card card1 = new Card(CardNumber.TEN, Pattern.HEART);
            Card card2 = new Card(CardNumber.FIVE, Pattern.HEART);
            Hand newHand = hand.add(card, card1, card2);

            Score score = newHand.calculateScore();
            assertThat(score).isEqualTo(new Score(16));
        }
    }
}
