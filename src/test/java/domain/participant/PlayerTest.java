package domain.participant;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.FIVE;
import static domain.card.Denomination.FOUR;
import static domain.card.Denomination.TEN;
import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PlayerTest {
    private final String name = "prin";
    private final int betAmount = 1000;

    @Test
    void 점수가_bust면_끝난_상태이므로_false를_반환한다() {
        Player player = new Player(name, betAmount);
        player.receiveAdditionalCard(카드(TEN));
        player.receiveAdditionalCard(카드(TEN));
        player.receiveAdditionalCard(카드(TEN));

        boolean result = player.isNotFinished();

        assertThat(result).isFalse();
    }

    @Test
    void 점수가_blackjack이면_끝난_상태이므로_false를_반환한다() {
        Player player = new Player(name, betAmount);
        player.receiveAdditionalCard(카드(ACE));
        player.receiveAdditionalCard(카드(TEN));

        boolean result = player.isNotFinished();

        assertThat(result).isFalse();
    }

    @Test
    void 점수가_bust도_아니고_blackjack도_아니면_끝나지_않은_상태이므로_true를_반환한다() {
        Player player = new Player(name, betAmount);
        player.receiveAdditionalCard(카드(FIVE));
        player.receiveAdditionalCard(카드(FOUR));

        boolean result = player.isNotFinished();

        assertThat(result).isTrue();
    }
}
