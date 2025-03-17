package model.participants;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("플레이어들 객체를 생성할 수 있다")
    @Test
    void invitePlayer() {
        Dealer dealer = new Dealer();
        dealer.initializeDealerWithHand();

        Players players = new Players();
        players.initializePlayersWithHand(dealer, List.of("a", "b"), List.of(1000, 2000));

        assertThat(players.getPlayers()).isNotNull();
    }
}
