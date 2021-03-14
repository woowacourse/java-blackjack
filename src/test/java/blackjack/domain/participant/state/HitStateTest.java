package blackjack.domain.participant.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class HitStateTest {
    @Test
    @DisplayName("게임을 계속 진행하는 HitState 객체를 생성하면, 정상적으로 반환된다")
    void initTest() {
        assertThatCode(() -> new HitState(Arrays.asList(
                new Card(CardLetter.TWO, CardSuit.HEART),
                new Card(CardLetter.THREE, CardSuit.HEART))))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("HitState에서 카드를 추가했을 때 21이 이하면, HitState를 반환한다")
    void hitStateTest() {
        HandState handState = new HitState(Arrays.asList(
                new Card(CardLetter.TWO, CardSuit.HEART),
                new Card(CardLetter.THREE, CardSuit.HEART)));

        handState = handState.add(new Card(CardLetter.FIVE, CardSuit.HEART));
        assertThat(handState).isInstanceOf(HitState.class);
    }

    @Test
    @DisplayName("HitState에서 카드를 추가했을 때 21이 넘어가면, BustState를 반환한다")
    void bustStateTest() {
        HandState handState = new HitState(Arrays.asList(
                new Card(CardLetter.TEN, CardSuit.HEART),
                new Card(CardLetter.NINE, CardSuit.HEART)));

        handState = handState.add(new Card(CardLetter.EIGHT, CardSuit.HEART));
        assertThat(handState).isInstanceOf(BustState.class);
    }

    @Test
    @DisplayName("HitState에서 isBust를 검사하면, false가 나온다")
    void isBustTest() {
        HandState handState = new HitState(Arrays.asList(
                new Card(CardLetter.TEN, CardSuit.HEART),
                new Card(CardLetter.NINE, CardSuit.HEART)));

        assertThat(handState.isBust()).isFalse();
    }

    @Test
    @DisplayName("HitState에서 isBlackjack를 검사하면, false가 나온다")
    void isBlackjackTest() {
        HandState handState = new HitState(Arrays.asList(
                new Card(CardLetter.TEN, CardSuit.HEART),
                new Card(CardLetter.NINE, CardSuit.HEART)));

        assertThat(handState.isBlackjack()).isFalse();
    }
}
