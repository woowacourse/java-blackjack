package blackjack.domain.participant.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class HitStateTest {
    private HandState handState;

    @BeforeEach
    void setUp() {
        handState = new InitialState();
        handState = handState.add(new Card(CardLetter.THREE, CardSuit.HEART));
        handState = handState.add(new Card(CardLetter.TEN, CardSuit.HEART));
    }

    @Test
    @DisplayName("히트 상태의 handState가 생성자의 매개변수로 넘어오면, HitState 객체가 정상 생성된다")
    void initTest() {
        assertThatCode(() -> new HitState(handState))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("히트 상태가 아닌 handState가 생성자의 매개변수로 넘어오면, 예외가 발생한다")
    void initExceptionTest() {
        handState = handState.add(new Card(CardLetter.KING, CardSuit.HEART));

        assertThatThrownBy(() -> new HitState(handState))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("히트 상태의 카드패가 아닙니다.");
    }

    @Test
    @DisplayName("HitState에서 카드를 추가했을 때 21이 이하면, HitState를 반환한다")
    void hitStateTest() {
        handState = handState.add(new Card(CardLetter.FIVE, CardSuit.HEART));
        assertThat(handState).isInstanceOf(HitState.class);
    }

    @Test
    @DisplayName("HitState에서 카드를 추가했을 때 21이 넘어가면, BustState를 반환한다")
    void bustStateTest() {
        handState = handState.add(new Card(CardLetter.KING, CardSuit.HEART));
        assertThat(handState).isInstanceOf(BustState.class);
    }

    @Test
    @DisplayName("HitState에서 isBust를 검사하면, false가 나온다")
    void isBustTest() {
        assertThat(handState.isBust()).isFalse();
    }

    @Test
    @DisplayName("HitState에서 isBlackjack를 검사하면, false가 나온다")
    void isBlackjackTest() {
        assertThat(handState.isBlackjack()).isFalse();
    }
}
