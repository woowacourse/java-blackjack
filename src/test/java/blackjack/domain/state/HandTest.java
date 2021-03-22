package blackjack.domain.state;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import blackjack.domain.state.hand.Hand;
import blackjack.domain.state.hand.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {

    @Test
    @DisplayName("손패에 카드 추가 테스트")
    void testAddCardInHand() {
        Hand hand = new Hand(new ArrayList<>());
        hand.addCard(new Card(Pattern.DIAMOND, Number.TWO));
        hand.addCard(new Card(Pattern.DIAMOND, Number.ACE));
        assertThat(hand.toList()).hasSize(2);
    }

    @Test
    @DisplayName("손패 점수 총합 테스트")
    void testHandsTotalScore() {
        Hand hand = new Hand(new ArrayList<>());
        hand.addCard(new Card(Pattern.CLOVER, Number.EIGHT));
        hand.addCard(new Card(Pattern.DIAMOND, Number.NINE));
        hand.addCard(new Card(Pattern.DIAMOND, Number.TEN));
        assertThat(hand.totalScore()).isEqualTo(new Score(27));
    }

    @Test
    @DisplayName("SoftAce에서 HardAce로 자동변환 테스트")
    void testAceAutoChange() {
        Hand hand = new Hand(new ArrayList<>());
        hand.addCard(new Card(Pattern.DIAMOND, Number.ACE));
        hand.addCard(new Card(Pattern.CLOVER, Number.ACE));
        hand.addCard(new Card(Pattern.HEART, Number.ACE));
        assertThat(hand.totalScore()).isEqualTo(new Score(13));
    }
}
