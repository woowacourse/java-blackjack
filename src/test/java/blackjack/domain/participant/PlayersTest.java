package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Betting;
import blackjack.domain.card.CardDeck;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("Players 안의 플레이어 리스트를 정상적으로 순회할 수 있다.")
    void iterate() {
        // given
        List<String> nameStrings = List.of("1", "2", "3", "4", "5", "6", "7", "8");
        List<String> actual = new ArrayList<>();

        Players players = new Players(new PlayerNames(nameStrings), CardDeck.createGameDeck(),
                name -> new Betting(1000));

        // when
        players.forEach(player -> actual.add(player.getName()));

        // then
        assertThat(actual).containsAll(nameStrings);
    }
}
