package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {
    @Test
    @DisplayName("현재 패의 점수 합계를 반환한다.")
    void totalScore() {
        Hand hand = new Hand();
        hand.add(new Card(CardType.DIAMOND, CardNumber.TWO));
        hand.add(new Card(CardType.DIAMOND, CardNumber.FIVE));
        Assertions.assertThat(hand.getResultScore())
                .isEqualTo(7);
    }

    @Test
    @DisplayName("DEFAULT_ACE가 포함되어 있고 bust가 아니면 총합을 반환한다")
    void notChangeAceScore() {
        Hand hand = new Hand();
        hand.add(new Card(CardType.DIAMOND, CardNumber.ACE));
        hand.add(new Card(CardType.DIAMOND, CardNumber.THREE));
        Assertions.assertThat(hand.getResultScore())
                .isEqualTo(14);
    }

    @Test
    @DisplayName("DEFAULT_ACE가 포함되어 있고 bust이면 22보다 작은 가능한 수 중 가장 큰 수를 반환한다")
    void changeAceScore() {
        Hand hand = new Hand();
        hand.add(new Card(CardType.DIAMOND, CardNumber.ACE));
        hand.add(new Card(CardType.DIAMOND, CardNumber.ACE));
        hand.add(new Card(CardType.DIAMOND, CardNumber.TEN));
        hand.add(new Card(CardType.DIAMOND, CardNumber.SIX));
        Assertions.assertThat(hand.getResultScore())
                .isEqualTo(18);
    }

    @Test
    @DisplayName("DEFAULT_ACE가 포함되어 있고 bust인데, 22보다 작은 가능한 수를 만들 수 없다면 가장 작은 수를 반환한다")
    void changeAceScoreBustCase() {
        Hand hand = new Hand();
        hand.add(new Card(CardType.DIAMOND, CardNumber.ACE));
        hand.add(new Card(CardType.DIAMOND, CardNumber.ACE));
        hand.add(new Card(CardType.DIAMOND, CardNumber.TEN));
        hand.add(new Card(CardType.DIAMOND, CardNumber.TEN));
        Assertions.assertThat(hand.getResultScore())
                .isEqualTo(22);
    }
}
