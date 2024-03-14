package blackjack.domain.batting;

import blackjack.domain.participant.Player;
import org.junit.jupiter.api.Test;

import static blackjack.fixture.PlayerFixture.플레이어;
import static org.assertj.core.api.Assertions.assertThat;

class BattingAmountRepositoryTest {

    @Test
    void 플레이어_별_배팅_금액을_기록하고_찾을_수_있다() {
        final BattingAmountRepository repository = new BattingAmountRepository();
        final Player player = 플레이어("pobi");
        final BattingAmount battingAmount = new BattingAmount(10000);

        repository.save(player, battingAmount);
        final BattingAmount findBattingAmount = repository.findByPlayer(player);

        assertThat(findBattingAmount).isEqualTo(battingAmount);
    }
}
