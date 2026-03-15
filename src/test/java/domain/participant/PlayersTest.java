package domain.participant;

import domain.money.Bet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayersTest {

    @Test
    @DisplayName("플레이어 인원 수는 5명 이하여야 합니다.")
    void 플레이어인원수_5명이하_성공() {
        // given
        List<Player> players = List.of(
                new Player(new Name("pobi"), new Bet(10000)),
                new Player(new Name("james"), new Bet(10000)));

        // when - then
        Assertions.assertDoesNotThrow(() -> new Players(players));
    }

    @Test
    @DisplayName("플레이어 인원 수는 6명 이상인 경우 오류가 발생해야 한다.")
    void 플레이어인원수_6명이상_오류() {
        // given
        List<Player> players = List.of(
                new Player(new Name("pobi"), new Bet(10000)),
                new Player(new Name("james"), new Bet(10000)),
                new Player(new Name("eunoh"), new Bet(10000)),
                new Player(new Name("ruro"), new Bet(10000)),
                new Player(new Name("rama"), new Bet(10000)),
                new Player(new Name("dudu"), new Bet(10000)));

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Players(players));
    }

    @Test
    @DisplayName("플레이어 이름이 중복되는 경우 오류가 발생해야 한다.")
    void 플레이어_이름_중복_금지() {
        // given
        List<Player> players = List.of(
                new Player(new Name("pobi"), new Bet(10000)),
                new Player(new Name("james"), new Bet(10000)),
                new Player(new Name("pobi"), new Bet(10000)));

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Players(players));
    }

    @Test
    @DisplayName("플레이어를 정확하게 찾는지 검증한다.")
    void findByTest() {
        // given
        Player targetPlayer = new Player(new Name("pobi"), new Bet(10000));

        List<Player> playerNames = List.of(targetPlayer, new Player(new Name("james"), new Bet(10000)));

        // when - then
        Players players = new Players(playerNames);

        assertEquals(players.findBy(targetPlayer), targetPlayer);
    }
}
