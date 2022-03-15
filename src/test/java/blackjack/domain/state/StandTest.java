package blackjack.domain.state;

import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.FIVE;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardPattern.SPADE;
import static blackjack.testutil.CardFixtureGenerator.createCards;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StandTest {

    @Test
    @DisplayName("Stand는 hit할 경우 예외가 발생해야 한다.")
    void hitException() {
        final BlackjackGameState stand = new Stand(createCards(Card.of(SPADE, KING), Card.of(SPADE, FIVE)));
        assertThatThrownBy(() -> stand.hit(Card.of(SPADE, A)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("종료된 상태는 hit을 할 수 없습니다.");
    }
}
