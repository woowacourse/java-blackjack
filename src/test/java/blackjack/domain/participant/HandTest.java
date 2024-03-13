package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardShape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {
    @DisplayName("패는 카드를 받을 수 있다.")
    @Test
    void add() {
        Hand hand = new Hand();

        hand = hand.add(Card.of(CardRank.EIGHT, CardShape.DIAMOND));

        List<Card> cards = hand.getCards();
        assertThat(cards).hasSize(1);
    }

    @DisplayName("패는 총 점수를 계산할 수 있다.")
    @Test
    void calculateScore() {
        Hand hand = new Hand();
        hand = hand.add(Card.of(CardRank.EIGHT, CardShape.DIAMOND));
        hand = hand.add(Card.of(CardRank.FOUR, CardShape.CLOVER));

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(12);
    }

    @DisplayName("에이스가 포함됐을 때 점수가 21 초과면 에이스를 1로 취급한다.")
    @Test
    void calculateScoreWithMinAce() {
        Hand hand = new Hand();
        hand = hand.add(Card.of(CardRank.ACE, CardShape.DIAMOND));
        hand = hand.add(Card.of(CardRank.KING, CardShape.CLOVER));
        hand = hand.add(Card.of(CardRank.JACK, CardShape.CLOVER));

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(21);
    }

    @DisplayName("에이스가 포함됐을 때 점수가 21 이하면 에이스를 11로 취급한다.")
    @Test
    void calculateScoreWithMaxAce() {
        Hand hand = new Hand();
        hand = hand.add(Card.of(CardRank.ACE, CardShape.DIAMOND));
        hand = hand.add(Card.of(CardRank.JACK, CardShape.CLOVER));

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(21);
    }

    @DisplayName("버스트 상태인지 알 수 있다.")
    @Test
    void isBust() {
        Hand hand = new Hand();
        hand = hand.add(Card.of(CardRank.JACK, CardShape.DIAMOND));
        hand = hand.add(Card.of(CardRank.KING, CardShape.CLOVER));
        hand = hand.add(Card.of(CardRank.TWO, CardShape.DIAMOND));

        boolean result = hand.isBust();

        assertThat(result).isTrue();
    }

    @DisplayName("블랙잭 상태인지 알 수 있다.")
    @Test
    void isBlackJack() {
        Hand hand = new Hand();
        hand = hand.add(Card.of(CardRank.ACE, CardShape.DIAMOND));
        hand = hand.add(Card.of(CardRank.KING, CardShape.CLOVER));

        boolean result = hand.isBlackJack();

        assertThat(result).isTrue();
    }

    @DisplayName("점수가 21점이라도 카드가 2장 넘으면 블랙잭 상태가 아니다.")
    @Test
    void isNotBlackJack() {
        Hand hand = new Hand();
        hand = hand.add(Card.of(CardRank.TWO, CardShape.DIAMOND));
        hand = hand.add(Card.of(CardRank.KING, CardShape.CLOVER));
        hand = hand.add(Card.of(CardRank.NINE, CardShape.DIAMOND));

        boolean result = hand.isBlackJack();

        assertThat(result).isFalse();
    }
}
