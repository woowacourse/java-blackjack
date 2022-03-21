package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.player.Gamers;
import blackjack.model.player.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamersTest {

    Map<String, Betting> gamerInfo = new LinkedHashMap<>() {
        {
            put("gamer1", new Betting(1000));
            put("gamer2", new Betting(1000));
            put("gamer3", new Betting(1000));
        }
    };

    @Test
    @DisplayName("Gamers 생성 테스트")
    void gamersCreateTest() {
        Gamers gamers = new Gamers(gamerInfo, new CardDeck());
        List<String> names = gamers.getGamers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
        assertThat(names).isEqualTo(List.of("gamer1", "gamer2", "gamer3"));
    }

    @Test
    @DisplayName("이름으로 Gamer 탐색 테스트")
    void findByNameTest() {
        Gamers gamers = new Gamers(gamerInfo, new CardDeck());
        assertThat(gamers.findGamerByName("gamer1").getBetting().getAmount()).isEqualTo(1000);
    }
}
