package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import domain.card.Card;
import domain.participant.Hand;
import domain.result.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {
    @Test
    @DisplayName("에이스 1장을 가진 상태에서 합계가 21을 넘으면 1점으로 계산하여 버스트를 방지한다")
    void checkBustWithOneAce() {
        Hand hand = new Hand();
        hand.add(new Card(0));
        hand.add(new Card(10));
        hand.add(new Card(1));

        Score score = hand.calculateScore();
        assertFalse(score.isBust());
        assertEquals(13, score.getGameScore());
    }

    @Test
    @DisplayName("에이스가 3장일 때도 점수 보정 후 합계가 정상 계산된다")
    void checkBustWithManyAces() {
        Hand hand = new Hand();
        hand.add(new Card(0));
        hand.add(new Card(13));
        hand.add(new Card(26));
        hand.add(new Card(10));

        Score score = hand.calculateScore();
        assertEquals(13, score.getGameScore());
    }

    @Test
    @DisplayName("에이스를 11로 계산하여 정확하게 21이 되는 경우 점수를 수정하지 않는다")
    void calculateScoreExact21() {
        Hand hand = new Hand();
        hand.add(new Card(0));
        hand.add(new Card(10));

        assertEquals(21, hand.calculateScore().getGameScore());
    }
}
