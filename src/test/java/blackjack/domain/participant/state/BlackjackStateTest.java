package blackjack.domain.participant.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class BlackjackStateTest {
    private HandState handState;

    @BeforeEach
    void setUp() {
        handState = new InitialState();
        handState = handState.add(new Card(CardLetter.ACE, CardSuit.HEART));
        handState = handState.add(new Card(CardLetter.KING, CardSuit.HEART));
    }

    @Test
    @DisplayName("BlackjackState 객체를 생성하면, 정상적으로 반환된다")
    void initTest() {
        assertThatCode(() -> new BlackjackState(Arrays.asList(
                new Card(CardLetter.ACE, CardSuit.HEART),
                new Card(CardLetter.KING, CardSuit.HEART))))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("BlackjackState 에서 새로운 카드를 받으려고 한다면, 예외가 발생한다")
    void addTest() {
        assertThatThrownBy(() -> handState.add(new Card(CardLetter.FIVE, CardSuit.HEART)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("블랙잭은 더 이상 카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("BlackjackState에서 isBust를 검사하면, false가 나온다")
    void isBustTest() {
        assertThat(handState.isBust()).isFalse();
    }

    @Test
    @DisplayName("BlackjackState에서 isBlackjack를 검사하면, true가 나온다")
    void isBlackjackTest() {
        assertThat(handState.isBlackjack()).isTrue();
    }
}
