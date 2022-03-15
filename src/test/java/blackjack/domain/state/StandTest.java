package blackjack.domain.state;

import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.FIVE;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardPattern.SPADE;
import static blackjack.testutil.CardFixtureGenerator.createCards;
import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    @DisplayName("Stand는 stand할 경우 예외가 발생해야 한다.")
    void standException() {
        final BlackjackGameState stand = new Stand(createCards(Card.of(SPADE, KING), Card.of(SPADE, FIVE)));
        assertThatThrownBy(() -> stand.stand())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("종료된 상태는 stand을 할 수 없습니다.");
    }

    @Test
    @DisplayName("Stand는 종료여부는 true가 반환된다.")
    void isFinished() {
        final BlackjackGameState stand = new Stand(createCards(Card.of(SPADE, KING), Card.of(SPADE, FIVE)));
        assertThat(stand.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Stand는 score를 계산할 수 있다.")
    void score() {
        final BlackjackGameState stand = new Stand(createCards(Card.of(SPADE, KING), Card.of(SPADE, FIVE)));
        assertThat(stand.score()).isEqualTo(15);
    }
}
