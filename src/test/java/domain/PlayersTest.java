package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.InitialCardMaker;

public class PlayersTest {

    private CardDistributor distributor;

    @BeforeEach
    void setUp() {
        distributor = new CardDistributor(InitialCardMaker.generate());
    }

    @Test
    @DisplayName("참여자 이름은 중복을 허용하지 않는다.")
    void createPlayersDuplicateFail() {
        List<String> playerNames = List.of("a", "a", "a");

        assertThatThrownBy(() -> createGamePlayers(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참여자 이름은 중복될 수 없습니다.");
    }

    private Players createGamePlayers(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(name -> distributeInitialCardForPlayer(name, distributor))
                .collect(Collectors.toList());
        return Players.from(players);
    }

    private Player distributeInitialCardForPlayer(String playerName, CardDistributor cardDistributor) {
        return Player.of(new Name(playerName), cardDistributor.distributeInitialCard());
    }

}
