package domain.gamer;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("플레이어는 처음 두장의 카드를 받는다.")
    @Test
    void initCardTest() {
        Players players = new PlayersCreator().create(List.of("hogee", "pola"), List.of(10000, 20000));
        players.initCard(new Dealer());
        for (Player player : players.getPlayers()) {
            Assertions.assertThat(player.getCardStatus().size()).isEqualTo(2);
        }
    }

}
