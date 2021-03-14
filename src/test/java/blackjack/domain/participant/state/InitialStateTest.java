package blackjack.domain.participant.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class InitialStateTest {
    @Test
    @DisplayName("초기 상태를 나타내는 InitialState 객체를 생성하면, 정상적으로 반환된다")
    void initTest() {
        assertThatCode(() -> new InitialState())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("InitialState에서 카드의 점수가 21인 카드 2장이 추가되면, BlackjackState를 반환한다")
    void blackjackStateTest() {
        HandState handState = new InitialState();

        handState = handState.add(new Card(CardLetter.ACE, CardSuit.HEART));
        assertThat(handState).isInstanceOf(InitialState.class);

        handState = handState.add(new Card(CardLetter.KING, CardSuit.HEART));
        assertThat(handState).isInstanceOf(BlackjackState.class);
    }

    @Test
    @DisplayName("InitialState에서 카드의 점수가 21 미만인 카드 2장이 추가되면, HitState를 반환한다")
    void hitStateTest() {
        HandState handState = new InitialState();

        handState = handState.add(new Card(CardLetter.TWO, CardSuit.HEART));
        assertThat(handState).isInstanceOf(InitialState.class);

        handState = handState.add(new Card(CardLetter.THREE, CardSuit.HEART));
        assertThat(handState).isInstanceOf(HitState.class);
    }
}
