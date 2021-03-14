package blackjack.domain.participant.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
    @DisplayName("BustState 객체를 생성하면, 정상적으로 반환된다")
    void initTest() {
        assertThatCode(() -> new BustState(Arrays.asList(
                new Card(CardLetter.JACK, CardSuit.HEART),
                new Card(CardLetter.FIVE, CardSuit.HEART),
                new Card(CardLetter.TEN, CardSuit.CLOVER))))
                .doesNotThrowAnyException();
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
