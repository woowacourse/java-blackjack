package blackjack.domain.participant.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class BustStateTest {
    private HandState handState;

    @BeforeEach
    void setUp() {
        handState = new InitialState();
        handState = handState.add(new Card(CardLetter.FIVE, CardSuit.HEART));
        handState = handState.add(new Card(CardLetter.TEN, CardSuit.HEART));
        handState = handState.add(new Card(CardLetter.KING, CardSuit.HEART));
    }

    @Test
    @DisplayName("버스트 상태의 handState가 생성자의 매개변수로 넘어오면, BustState 객체가 정상 생성된다")
    void initTest() {
        assertThatCode(() -> new BustState(handState))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("버스트 상태가 아닌 handState가 생성자의 매개변수로 넘어오면, 예외가 발생한다")
    void initExceptionTest() {
        handState = new InitialState();
        handState = handState.add(new Card(CardLetter.TWO, CardSuit.HEART));
        handState = handState.add(new Card(CardLetter.KING, CardSuit.HEART));

        assertThatThrownBy(() -> new BustState(handState))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("버스트 상태의 카드패가 아닙니다.");
    }

    @Test
    @DisplayName("BustState 에서 새로운 카드를 받으려고 한다면, 예외가 발생한다")
    void addTest() {
        assertThatThrownBy(() -> handState.add(new Card(CardLetter.FIVE, CardSuit.HEART)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("버스트는 더 이상 카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("BustState에서 isBust를 검사하면, true가 나온다")
    void isBustTest() {
        assertThat(handState.isBust()).isTrue();
    }

    @Test
    @DisplayName("BustState에서 isBlackjack를 검사하면, false가 나온다")
    void isBlackjackTest() {
        assertThat(handState.isBlackjack()).isFalse();
    }
}
