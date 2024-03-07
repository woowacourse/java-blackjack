package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.common.Names;
import blackjack.domain.player.Player;
import blackjack.fixture.CardFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("이름 목록을 통해 플레이어를 생성하고 딜러와 플레이어에게 카드를 두장씩 나눠준다.")
    public void Blackjack_Accept_players() {
        Blackjack blackjack = new Blackjack(CardFixture.카드_덱_생성());
        Names names = Names.from(List.of("초롱", "조이썬"));

        var result = blackjack.acceptPlayers(names);

        assertPlayer(result.getDealer(), 8);

        assertPlayer(result.getGamePlayers()
                           .get(0), 13);
        assertPlayer(result.getGamePlayers()
                           .get(1), 15);
    }

    private void assertPlayer(Player player, int value) {
        assertThat(player.calculateScore()).isEqualTo(value);
    }

}
