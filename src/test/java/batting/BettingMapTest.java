package batting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import participants.Name;
import participants.Player;

class BettingMapTest {
    BettingMap bettingMap;

    @BeforeEach
    void setUp() {
        bettingMap = new BettingMap();
    }

    @Test
    @DisplayName("베팅맵에 플레이어와 어마운트를 저장한다")
    void saveBet() {
        Player player = new Player(new Name("폴로"));
        Amount amount = new Amount(1000);

        bettingMap.saveBet(player, amount);

        assertThat(bettingMap.getBettingAmountByPlayer(player)).isEqualTo(amount);
    }
}
