package blackjack.domain.participant.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class InitialStateTest {
    private HandState handState;

    @BeforeEach
    void setUp() {
        handState = new InitialState();
    }

    @Test
    @DisplayName("초기 상태를 나타내는 InitialState 객체를 생성하면, 정상적으로 반환된다")
    void initTest() {
        assertThatCode(() -> new InitialState())
                .doesNotThrowAnyException();

        handState = handState.add(new Card(CardLetter.KING, CardSuit.HEART));
        assertThatCode(() -> new InitialState(handState))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("초기 상태가 아닌 handState가 생성자의 매개변수로 넘어오면, 예외가 발생한다")
    void initExceptionTest() {
        handState = handState.add(new Card(CardLetter.KING, CardSuit.HEART));
        handState = handState.add(new Card(CardLetter.QUEEN, CardSuit.HEART));

        assertThatThrownBy(() -> new InitialState(handState))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("2장의 카드부터는 초기 상태의 카드패가 될 수 없습니다.");
    }

    @Test
    @DisplayName("InitialState에서 카드의 점수가 21인 카드 2장이 추가되면, BlackjackState를 반환한다")
    void blackjackStateTest() {
        handState = handState.add(new Card(CardLetter.ACE, CardSuit.HEART));
        assertThat(handState).isInstanceOf(InitialState.class);

        handState = handState.add(new Card(CardLetter.KING, CardSuit.HEART));
        assertThat(handState).isInstanceOf(BlackjackState.class);
    }

    @Test
    @DisplayName("InitialState에서 카드의 점수가 21 미만인 카드 2장이 추가되면, HitState를 반환한다")
    void hitStateTest() {
        handState = handState.add(new Card(CardLetter.TWO, CardSuit.HEART));
        assertThat(handState).isInstanceOf(InitialState.class);

        handState = handState.add(new Card(CardLetter.THREE, CardSuit.HEART));
        assertThat(handState).isInstanceOf(HitState.class);
    }

    @Test
    @DisplayName("InitialState에서 isBust를 검사하면, false가 나온다")
    void isBustTest() {
        assertThat(handState.isBust()).isFalse();

        handState = handState.add(new Card(CardLetter.TEN, CardSuit.HEART));
        assertThat(handState.isBust()).isFalse();
    }

    @Test
    @DisplayName("InitialState에서 isBlackjack를 검사하면, false가 나온다")
    void isBlackjackTest() {
        assertThat(handState.isBlackjack()).isFalse();

        handState = handState.add(new Card(CardLetter.TEN, CardSuit.HEART));
        assertThat(handState.isBlackjack()).isFalse();
    }
}
