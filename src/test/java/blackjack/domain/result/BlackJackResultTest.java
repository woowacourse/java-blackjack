package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.Bet;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackResultTest {

    @Test
    @DisplayName("최초의 블랙잭 결과판을 만들 수 있다.")
    void createInitialBlackJackResult() {
        Gamer huni = new Gamer("huni", new Bet(100));
        Gamer hani = new Gamer("hani", new Bet(100));

        Gamers gamers = new Gamers(List.of(huni, hani));

        Map<Player, ResultStrategy> map = gamers.getGamers().stream()
                .collect(Collectors.toMap(gamer -> gamer,
                        gamer -> new Win()));

        BlackJackResult result = new BlackJackResult(map);
        assertThat(result.getResult().keySet()).contains(huni, hani);
    }
}
