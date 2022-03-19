package blackjack.domain.state;

import static blackjack.domain.CardFixture.SPADE_ACE;
import static blackjack.domain.CardFixture.SPADE_TEN;
import static blackjack.domain.CardFixture.SPADE_THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantBlackjackTest {

    private State blackjack;

    @BeforeEach
    void setUp() {
        blackjack = Ready.dealToParticipant(SPADE_ACE, SPADE_TEN);
    }

    @Test
    @DisplayName("참가자가 blackjack이면 ParticipantBlackjack이 된다.")
    void dealer_bust() {
        assertThat(blackjack).isInstanceOf(ParticipantBlackjack.class);
    }

    @Test
    @DisplayName("blackjack일 때 draw를 하면 에러가 발생한다.")
    void draw() {
        assertThatThrownBy(() -> blackjack.draw(SPADE_THREE))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("blackjack일 때 stay를 하면 에러가 발생한다.")
    void stay() {
        assertThatThrownBy(blackjack::stay)
                .isInstanceOf(IllegalStateException.class);
    }
}
