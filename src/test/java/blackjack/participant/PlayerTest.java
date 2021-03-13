package blackjack.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.BettingMoney;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("플레이어를 생성한다.")
    @Test
    void createPlayerTest() {
        Player player = new Player(new Name("파피"), new BettingMoney(2000));
        assertThat(player.getName()).isEqualTo("파피");
        assertThat(player.getBettingMoney()).isEqualTo(2000);
    }
}
