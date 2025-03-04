package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("플레이어 리스트 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayersTest {

    @Test
    void 플레이어별_이름과_카드리스트의_총합을_반환한다() {
        Player drago = new Player("드라고", new Cards(
                List.of(new Card(Symbol.DIAMOND, Number.EIGHT),
                        new Card(Symbol.DIAMOND, Number.JACK),
                        new Card(Symbol.HEART, Number.FOUR))));

        Player duei = new Player("듀이", new Cards(
                List.of(new Card(Symbol.DIAMOND, Number.EIGHT),
                        new Card(Symbol.DIAMOND, Number.JACK),
                        new Card(Symbol.HEART, Number.TWO))));

        Players players = new Players(List.of(drago, duei));
        Map<String, Integer> expected = Map.of("드라고", 22, "듀이", 20);

        assertThat(players.getTotalNumberSumByName()).isEqualTo(expected);
    }
}
