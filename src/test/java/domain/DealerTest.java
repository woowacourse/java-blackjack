package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

import controller.dto.dealer.DealerHandScore;
import controller.dto.dealer.DealerHandStatus;
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

    @DisplayName("딜러가 가지고 있는 카드의 정보와 총 점수 정보를 반환한다.")
    @Test
    void getDealerHandScore() {
        Hand hand = underThresholdHand();
        Dealer dealer = new Dealer(hand);

        DealerHandScore dealerHandScore = dealer.getCurrentDealerHandScore();
        DealerHandStatus dealerHandStatus = dealerHandScore.dealerHandStatus();

        assertAll(
                () -> assertThat(dealerHandStatus.hands()).isEqualTo("10스페이드, 6하트"),
                () -> assertThat(dealerHandScore.score()).isEqualTo(16)
        );
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
