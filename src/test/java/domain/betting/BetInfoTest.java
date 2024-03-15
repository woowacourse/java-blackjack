package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetInfoTest {

    @Test
    @DisplayName("베팅 정보를 추가할 수 있다.")
    void add() {
        BetInfo betInfo = BetInfo.withNoEntry();
        Player player = Player.withName("user1");
        Money betAmount = Money.valueOf(1000);

        betInfo.add(player, betAmount);

        assertThat(betInfo.findBetAmountBy(player)).isEqualTo(betAmount);
    }
}
