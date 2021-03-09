package blackjack.domain.participant.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    private Card card;
    private State blackjack;

    @BeforeEach
    void setUp() {
        Card firstCard = Card.valueOf(Pattern.HEART, Number.ACE);
        Card secondCard = Card.valueOf(Pattern.DIAMOND, Number.KING);
        this.blackjack = InitDraw.draw(firstCard, secondCard);
        this.card = Card.valueOf(Pattern.HEART, Number.KING);
    }

    @Test
    @DisplayName("첫 2장을 받은 후 상태가 Blackjack인지 테스트")
    void testFromInitDrawToHit() {
        assertThat(this.blackjack).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("Blackjack 에서 draw 요청시 에러처리")
    void testDrawException() {
        assertThatThrownBy(() -> this.blackjack.draw(this.card))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Blackjack 에서 stay 요청시 에러처리")
    void testStayException() {
        assertThatThrownBy(() -> this.blackjack.stay())
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Blackjack 가 종료된 상태임을 확인")
    void testIsFinishedTrue() {
        assertThat(this.blackjack.isFinished()).isTrue();
    }
}