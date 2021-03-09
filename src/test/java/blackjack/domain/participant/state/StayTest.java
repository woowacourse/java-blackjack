package blackjack.domain.participant.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StayTest {

    private State stay;
    private Card card;

    @BeforeEach
    void setUp() {
        this.card = Card.valueOf(Pattern.HEART, Number.FIVE);
        this.stay = new Stay(Collections.singletonList(this.card));
    }

    @Test
    @DisplayName("Stay 에서 draw 요청시 에러처리")
    void testDrawException() {
        assertThatThrownBy(() -> this.stay.draw(this.card))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Stay 에서 stay 요청시 에러처리")
    void testStayException() {
        assertThatThrownBy(() -> this.stay.stay())
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Stay 가 종료된 상태임을 확인")
    void testIsFinishedTrue() {
        assertThat(this.stay.isFinished()).isTrue();
    }
}