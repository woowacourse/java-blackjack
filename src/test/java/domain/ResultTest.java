package domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultTest {

    @Test
    void 생성자를_통해_calculateWinCount_메서드_테스트() {
        //given
        Bettings bettings = new Bettings(List.of(new Betting("10000"),new Betting("5000")));
        Players players = new Players(List.of(
                new Player(new PlayerName("judy"),
                        new Cards(
                                List.of(new Card(Suit.CLOVER, Denomination.KING))))));

        Dealer dealer = new Dealer(
                new Cards(
                        List.of(new Card(Suit.CLOVER, Denomination.KING),
                                new Card(Suit.HEART, Denomination.QUEEN),
                                new Card(Suit.HEART, Denomination.ACE))));

        //when
        Result result = new Result(players, dealer, bettings);
        Map<Gambler, Integer> map = result.getWinOrLoseResult();
        int expected = 1;
        int actual = map.get(dealer);

        //then
        assertEquals(expected, actual);
    }
}
