package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("플레이어 리스트 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayersTest {

    @Test
    void 플레이어별_이름과_카드리스트의_총합을_반환한다() {
        Player drago = new Player(new Name("drago"), new Cards(
                List.of(new Card(Symbol.DIAMOND, Number.EIGHT),
                        new Card(Symbol.DIAMOND, Number.JACK),
                        new Card(Symbol.HEART, Number.FOUR))));

        Player duei = new Player(new Name("duei"), new Cards(
                List.of(new Card(Symbol.DIAMOND, Number.EIGHT),
                        new Card(Symbol.DIAMOND, Number.JACK),
                        new Card(Symbol.HEART, Number.TWO))));

        Players players = new Players(List.of(drago, duei));
        Map<String, Integer> expected = Map.of("drago", 22, "duei", 20);

        assertThat(players.getTotalNumberSumByName()).isEqualTo(expected);
    }

    @Test
    void 이름으로_플레이어를_찾는다() {
        String drago = "drago";

        Cards cards = new Cards(List.of(new Card(Symbol.DIAMOND, Number.EIGHT), new Card(Symbol.CLOVER, Number.FOUR)));
        Players players = new Players(List.of(new Player(new Name(drago), cards)));

        Player player = players.findPlayer(drago);

        Player expected = new Player(new Name(drago), cards);
        assertThat(player).isEqualTo(expected);
    }

    @Test
    void 플레이어를_새로운_플레이어로_교체한다() {
        Cards cards = new Cards(List.of(new Card(Symbol.DIAMOND, Number.EIGHT), new Card(Symbol.CLOVER, Number.FOUR)));
        Player player = new Player(new Name("drago"), cards);
        Players players = new Players(new ArrayList<>(List.of(player)));

        Cards newCards = new Cards(List.of(new Card(Symbol.DIAMOND, Number.EIGHT),
                new Card(Symbol.CLOVER, Number.FOUR), new Card(Symbol.HEART, Number.JACK)));
        Player newPlayer = new Player(new Name("drago"), newCards);
        Players expected = new Players(List.of(newPlayer));

        assertThat(players.editPlayer(player, newPlayer)).isEqualTo(expected);
    }
}
