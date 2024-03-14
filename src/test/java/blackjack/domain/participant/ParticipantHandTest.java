package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantHandTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(ParticipantHand::new)
                .doesNotThrowAnyException();
    }

    @DisplayName("초기 패에는 아무 카드도 존재하지 않는다.")
    @Test
    void initialHandIsEmpty() {
        ParticipantHand hand = new ParticipantHand();

        List<Card> cards = hand.getCards();

        assertThat(cards).isEmpty();
    }

    @DisplayName("패는 카드를 받을 수 있다.")
    @Test
    void add() {
        ParticipantHand hand = new ParticipantHand();
        Card givenCard = new Card(CardRank.EIGHT, CardShape.DIAMOND);

        hand.add(givenCard);

        assertThat(hand.getCards()).containsExactly(givenCard);
    }

    @DisplayName("패는 총 점수를 계산할 수 있다.")
    @Test
    void calculateScore() {
        ParticipantHand hand = new ParticipantHand();
        hand.add(new Card(CardRank.EIGHT, CardShape.DIAMOND));
        hand.add(new Card(CardRank.FOUR, CardShape.CLOVER));

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(12);
    }

    @DisplayName("에이스가 포함됐을 때 점수가 21 초과면 에이스를 1로 취급한다.")
    @Test
    void calculateScoreWithMinAce() {
        ParticipantHand hand = new ParticipantHand();
        hand.add(new Card(CardRank.ACE, CardShape.DIAMOND));
        hand.add(new Card(CardRank.KING, CardShape.CLOVER));
        hand.add(new Card(CardRank.JACK, CardShape.CLOVER));

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(21);
    }

    @DisplayName("에이스가 포함됐을 때 점수가 21 이하면 에이스를 11로 취급한다.")
    @Test
    void calculateScoreWithMaxAce() {
        ParticipantHand hand = new ParticipantHand();
        hand.add(new Card(CardRank.ACE, CardShape.DIAMOND));
        hand.add(new Card(CardRank.JACK, CardShape.CLOVER));

        int score = hand.calculateScore();

        assertThat(score).isEqualTo(21);
    }

    @DisplayName("버스트 상태인지 알 수 있다.")
    @Test
    void isBust() {
        ParticipantHand hand = new ParticipantHand();
        hand.add(new Card(CardRank.JACK, CardShape.DIAMOND));
        hand.add(new Card(CardRank.KING, CardShape.CLOVER));
        hand.add(new Card(CardRank.TWO, CardShape.DIAMOND));

        boolean result = hand.isBust();

        assertThat(result).isTrue();
    }

    @DisplayName("블랙잭 상태인지 알 수 있다.")
    @Test
    void isBlackJack() {
        ParticipantHand hand = new ParticipantHand();
        hand.add(new Card(CardRank.ACE, CardShape.DIAMOND));
        hand.add(new Card(CardRank.KING, CardShape.CLOVER));

        boolean result = hand.isBlackJack();

        assertThat(result).isTrue();
    }

    @DisplayName("단순히 점수가 21인 경우에는 블랙잭이 아니다.")
    @Test
    void isNotBlackJack() {
        ParticipantHand hand = new ParticipantHand();
        hand.add(new Card(CardRank.KING, CardShape.CLOVER));
        hand.add(new Card(CardRank.QUEEN, CardShape.CLOVER));
        hand.add(new Card(CardRank.ACE, CardShape.CLOVER));

        boolean result = hand.isBlackJack();

        assertThat(result).isFalse();
    }
}
