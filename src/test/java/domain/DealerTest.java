package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import domain.constants.Score;
import domain.constants.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @DisplayName("딜러의 카드 점수 총점이 추가로 카드 뽑을 수 있는 점수의 상한인 16을 초과하는지 확인한다.")
    @Test
    void isUpToThreshold() {
        Hand hand = overThresholdHand();

        Dealer dealer = new Dealer(hand);

        boolean isNotUp = dealer.isNotUpToThreshold();
        assertFalse(isNotUp);
    }

    @DisplayName("딜러의 점수가 16점 이하일 때까지 카드를 뽑고, 그 횟수를 반환한다.")
    @Test
    void cardDrawCount() {
        Hand hand = underThresholdHand();

        Dealer dealer = new Dealer(hand);

        int count = dealer.cardDrawCount();
        assertThat(count).isEqualTo(1);
    }

    private Hand overThresholdHand() {
        Hand hand = new Hand();
        hand.saveCards(new ArrayList<>(Arrays.asList(
                new Card(Score.KING, Shape.SPADE),
                new Card(Score.KING, Shape.HEART)
        )));
        return hand;
    }

    private Hand underThresholdHand() {
        Hand hand = new Hand();
        hand.saveCards(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.SPADE),
                new Card(Score.SIX, Shape.HEART)
        )));
        return hand;
    }
}
