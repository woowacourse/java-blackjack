package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    void 점수_계산_확인_테스트() {
        Hand hand = HandFixture.createHand(List.of(
                Card.of(CardNumber.ACE, CardShape.CLOVER),
                Card.of(CardNumber.ACE, CardShape.DIAMOND)));

        assertThat(hand.getScore().value()).isEqualTo(12);
    }

    @Test
    void 블랙잭인_경우_확인() {
        Hand blackjack = HandFixture.createBlackjack();

        assertThat(blackjack.isBlackjack()).isTrue();
    }

    @Test
    void 블랙잭_점수이지만_블랙잭이_아닌_경우() {
        Hand blackjackScore = HandFixture.createBlackjackScore();

        assertThat(blackjackScore.isBlackjack()).isFalse();
    }

    @Test
    void 블랙잭이_아닌_경우() {
        Hand hand = HandFixture.createDefault();

        assertThat(hand.isBlackjack()).isFalse();
    }

    @Test
    void 버스트_여부_확인() {
        Hand hand = HandFixture.createBust();

        assertThat(hand.isBust()).isTrue();
    }

    @Test
    void 버스트가_아닌_경우() {
        Hand hand = HandFixture.createHand(List.of(
                Card.of(CardNumber.J, CardShape.CLOVER),
                Card.of(CardNumber.K, CardShape.DIAMOND)));

        assertThat(hand.isBust()).isFalse();
    }
}
