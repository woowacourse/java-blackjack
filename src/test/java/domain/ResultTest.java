package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultTest {

    @Test
    void 생성자를_통해_calculateWinCount_메서드_테스트() {
        //given
        List<String> playerNames = List.of("judy", "pobi");
        List<Player> players = getPlayers(playerNames);
        Players playersEntity = new Players(playerNames);

        Dealer dealer = new Dealer(
                new Cards(
                        List.of(new Card(Suit.CLOVER, Denomination.KING),
                                new Card(Suit.HEART, Denomination.ACE))));

        //when
        Result result = new Result(playersEntity, dealer);
        Map<Gambler, Integer> map = result.getResult();
        int expected = 2;
        int actual = map.get(dealer);

        //then
        assertEquals(expected, actual);
    }

    private List<Player> getPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();

        for (String playerName : playerNames) {
            players.add(new Player(
                    new PlayerName(playerName),
                    new Cards(
                            List.of(new Card(Suit.CLOVER, Denomination.FIVE),
                                    new Card(Suit.HEART, Denomination.TWO)))));
        }
        return players;
    }
}
