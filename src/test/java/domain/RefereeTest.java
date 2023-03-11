package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class RefereeTest {

    @Test
    void decideWinner_메서드로_승리한_Dealer의_value_증가() {
        //given
        Cards cards = new Cards(List.of(
                new Card(Suit.CLOVER, Denomination.TWO),
                new Card(Suit.CLOVER, Denomination.FIVE)));
        Player player = new Player(new PlayerName("judy"), cards);
        Dealer dealer = new Dealer(new Cards(List.of(new Card(Suit.CLOVER, Denomination.KING))));

        Map<Gambler, Integer> result = new LinkedHashMap<>();
        result.put(dealer, 0);

        //when
        Referee.decideWinner(player, dealer, result);

        //then
        Assertions.assertEquals(result.get(player), 0);
        Assertions.assertEquals(result.get(dealer), 1);
    }
}
