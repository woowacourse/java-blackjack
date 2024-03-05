package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Hand hand = new Hand();

        List<Card> cards = hand.getCards();

        assertThat(cards).isEmpty();
    }

    @DisplayName("패는 카드를 받을 수 있다.")
    @Test
    void add() {
        Hand hand = new Hand();

        hand.add(new Card(CardRank.EIGHT, CardShape.DIAMOND));

        List<Card> cards = hand.getCards();
        assertThat(cards).hasSize(1);
    }

    @DisplayName("패는 총 점수를 계산할 수 있다.")
    @Test
    void calculateScore() {
        Hand hand = new Hand();
        hand.add(new Card(CardRank.EIGHT, CardShape.DIAMOND));
        hand.add(new Card(CardRank.FOUR, CardShape.CLOVER));

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(12);
    }

    @DisplayName("에이스가 포함됐을 때 점수가 21 초과면 에이스를 1로 취급한다.")
    @Test
    void calculateScoreWithMinAce() {
        Hand hand = new Hand();
        hand.add(new Card(CardRank.ACE, CardShape.DIAMOND));
        hand.add(new Card(CardRank.KING, CardShape.CLOVER));
        hand.add(new Card(CardRank.JACK, CardShape.CLOVER));

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(21);
    }

    @DisplayName("에이스가 포함됐을 때 점수가 21 이하면 에이스를 11로 취급한다.")
    @Test
    void calculateScoreWithMaxAce() {
        Hand hand = new Hand();
        hand.add(new Card(CardRank.ACE, CardShape.DIAMOND));
        hand.add(new Card(CardRank.JACK, CardShape.CLOVER));

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(21);
    }
}
