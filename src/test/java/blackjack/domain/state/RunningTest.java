package blackjack.domain.state;

import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardPattern.SPADE;
import static blackjack.testutil.CardFixtureGenerator.createCards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {

    @Test
    @DisplayName("생성 시 null카드가 들어올 경우 예외를 발생시킨다.")
    void createExceptionByNullCards() {
        assertThatThrownBy(() -> new Running(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("cards는 null이 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("Running은 isFinished를 false 반환한다.")
    void isFinished() {
        Running running = new Running(createCards(Card.of(SPADE, KING), Card.of(SPADE, QUEEN)));
        assertThat(running.isFinished()).isFalse();
    }
}
