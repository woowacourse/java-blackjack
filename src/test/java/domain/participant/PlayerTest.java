package domain.participant;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Denomination;
import org.junit.jupiter.api.Test;

class PlayerTest {
    private final Name name = new Name("prin");
    private final BetAmount betAmount = new BetAmount(1);

    @Test
    void 점수가_bust면_끝난_상태이므로_false를_반환한다() {
        Player player = new Player(name, betAmount);
        player.receiveAdditionalCard(카드(Denomination.TEN));
        player.receiveAdditionalCard(카드(Denomination.TEN));
        player.receiveAdditionalCard(카드(Denomination.TEN));

        boolean result = player.isNotFinished();

        assertThat(result).isFalse();
    }

    @Test
    void 점수가_blackjack이면_끝난_상태이므로_false를_반환한다() {
        Player player = new Player(name, betAmount);
        player.receiveAdditionalCard(카드(Denomination.ACE));
        player.receiveAdditionalCard(카드(Denomination.TEN));

        boolean result = player.isNotFinished();

        assertThat(result).isFalse();
    }

    @Test
    void 점수가_bust도_아니고_blackjack도_아니면_끝나지_않은_상태이므로_true를_반환한다() {
        Player player = new Player(name, betAmount);
        player.receiveAdditionalCard(카드(Denomination.FIVE));
        player.receiveAdditionalCard(카드(Denomination.FOUR));

        boolean result = player.isNotFinished();

        assertThat(result).isTrue();
    }
}
