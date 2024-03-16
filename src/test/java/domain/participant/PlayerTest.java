package domain.participant;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Denomination;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    void 점수가_bust면_true를_반환한다() {
        // given
        Player player = new Player("prin");
        player.receiveAdditionalCard(카드(Denomination.TEN));
        player.receiveAdditionalCard(카드(Denomination.TEN));
        player.receiveAdditionalCard(카드(Denomination.TEN));

        // when
        boolean result = player.isNotFinished();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 점수가_bust가_아니면_false를_반환한다() {
        // given
        Player player = new Player("prin");
        player.receiveAdditionalCard(카드(Denomination.FIVE));
        player.receiveAdditionalCard(카드(Denomination.FOUR));

        // when
        boolean result = player.isNotFinished();

        // then
        assertThat(result).isFalse();
    }
}
