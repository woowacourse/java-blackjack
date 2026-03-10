package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testutil.HandTestUtil;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class HandTest {
    @DisplayName("블랙잭 계산")
    @Test
    void 블랙잭_점수_계산() {
        Hand hand = HandTestUtil.createHand(List.of(
                new Card(CardShape.SPADE, CardRank.ACE),
                new Card(CardShape.SPADE, CardRank.TEN)
        ));
        assertThat(hand.calculateFinalScore()).isEqualTo(21);
    }

    @DisplayName("점수가 21인 경우 계산")
    @Test
    void 점수_21인_패() {
        Hand hand = HandTestUtil.createHand(List.of(
                new Card(CardShape.SPADE, CardRank.ACE),
                new Card(CardShape.SPADE, CardRank.FOUR),
                new Card(CardShape.SPADE, CardRank.SIX)
        ));

        assertThat(hand.calculateFinalScore()).isEqualTo(21);
    }
}
