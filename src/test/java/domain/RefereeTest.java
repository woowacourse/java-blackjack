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
        Bettings bettings = new Bettings(List.of("10000"));
        Dealer dealer = new Dealer(new Cards(List.of(new Card(Suit.CLOVER, Denomination.KING))));
        Referee referee = new Referee(dealer, bettings);

        //when
        Player player = new Player(new PlayerName("judy"), cards);
        Map<Gambler, Integer> result = new LinkedHashMap<>();
        result.put(dealer, 0);
        referee.decideWinner(player, result);

        //then
        Assertions.assertEquals(result.get(player), 0);
        Assertions.assertEquals(result.get(dealer), 1);
    }

    @Test
    void decideBenefits_메서드로_수익_계산() {
        //given
        Cards cards = new Cards(List.of(
                new Card(Suit.CLOVER, Denomination.TWO),
                new Card(Suit.CLOVER, Denomination.FIVE)));
        Bettings bettings = new Bettings(List.of("10000"));
        Dealer dealer = new Dealer(new Cards(List.of(new Card(Suit.CLOVER, Denomination.KING))));
        Referee referee = new Referee(dealer, bettings);

        //when
        Player player = new Player(new PlayerName("judy"), cards);
        List<Player> players = List.of(player);
        Map<Gambler, Integer> benefits = new LinkedHashMap<>();
        benefits.put(dealer, 0);
        referee.decideBenefits(player, players, benefits);

        //then
        Assertions.assertEquals(benefits.get(player), -10000);
        Assertions.assertEquals(benefits.get(dealer), 10000);
    }

    @Test
    void 생성_테스트() {
        //given, when, then
        Assertions.assertDoesNotThrow(() -> new Referee(
                new Dealer(new Cards(List.of(
                        new Card(Suit.CLOVER, Denomination.TWO),
                        new Card(Suit.CLOVER, Denomination.FIVE)))),
                new Bettings(List.of("10000"))));
    }
}
