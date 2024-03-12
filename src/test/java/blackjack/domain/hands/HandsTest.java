package blackjack.domain.hands;

import blackjack.domain.card.Card;
import blackjack.domain.card.Kind;
import blackjack.domain.card.Value;
import blackjack.domain.hands.Hands;
import blackjack.domain.hands.HandsScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HandsTest {

    @DisplayName("카드패에 카드를 추가할 수 있다")
    @Test
    void should_addCard_IntoHands() {
        Hands hands = new Hands();
        hands.addCard(new Card(Kind.CLOVER, Value.ACE));
        assertThat(hands.getHands()).hasSize(1);
    }

    @DisplayName("카드패의 총 점수는 보유한 카드 점수들의 합이다")
    @Test
    void should_HandsScore_Equals_SumOfCardScores() {
        Hands hands = new Hands();

        hands.addCard(new Card(Kind.CLOVER, Value.TWO));
        hands.addCard(new Card(Kind.HEART, Value.TWO));

        assertThat(hands.getHandsScore()).isEqualTo(new HandsScore(4));
    }

    @DisplayName("에이스는 1 또는 11로 사용할 수 있다")
    @Test
    void should_downgradeAce() {
        Hands hands = new Hands();
        assertAll(
                () -> {
                    hands.addCard(new Card(Kind.CLOVER, Value.ACE));
                    assertThat(hands.getHandsScore()).isEqualTo(new HandsScore(11));
                },
                () -> {
                    hands.addCard(new Card(Kind.HEART, Value.ACE));
                    assertThat(hands.getHandsScore()).isEqualTo(new HandsScore(12));
                });
    }
}
