package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandsTest {

    @DisplayName("카드패에 카드를 추가할 수 있다")
    @Test
    void should_addCard_IntoHands() {
        Hands hands = new Hands();

        hands.addCard(Card.create(1));
        assertThat(hands.getHands()).hasSize(1);

        hands.addCard(Card.create(13));
        assertThat(hands.getHands()).hasSize(2);
    }

    @DisplayName("카드패의 총 점수는 보유한 카드 점수들의 합이다")
    @Test
    void should_HandsScore_Equals_SumOfCardScores() {
        Hands hands = new Hands();

        hands.addCard(Card.create(1));
        hands.addCard(Card.create(12));

        assertThat(hands.getHandsScore()).isEqualTo(12);
    }

    @DisplayName("에이스는 1 또는 11로 사용할 수 있다")
    @Test
    void should_downgradeAce() {
        Hands hands = new Hands();
        hands.addCard(Card.create(0));

        assertThat(hands.getHandsScore()).isEqualTo(11);

        hands.downgradeAce();
        assertThat(hands.getHandsScore()).isOne();
    }
}
