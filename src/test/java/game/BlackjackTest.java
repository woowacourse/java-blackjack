package game;

import domain.Bettings;
import domain.Card;
import domain.Cards;
import domain.Dealer;
import domain.Denomination;
import domain.Player;
import domain.PlayerName;
import domain.Players;
import domain.Result;
import domain.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BlackjackTest {

    @Test
    void 딜러가_가진_수가_플레이어보다_높으면_이긴다() {
        //given
        Bettings bettings = new Bettings(List.of("10000", "20000"));
        Player player = new Player(new PlayerName("judy"),
                new Cards(List.of(
                        new Card(Suit.CLOVER, Denomination.KING),
                        new Card(Suit.HEART, Denomination.QUEEN))
                ));


        Dealer dealer = new Dealer(new Cards(List.of(
                new Card(Suit.CLOVER, Denomination.KING),
                new Card(Suit.HEART, Denomination.QUEEN),
                new Card(Suit.HEART, Denomination.ACE)
        )));

        Blackjack blackjack = new Blackjack(List.of(player));
        Players players = blackjack.getPlayers();
        Result result = blackjack.createResult(players, dealer, bettings);

        //when
        int expected = 1;
        int actual = result.getResult().get(dealer);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void 플레이어가_가진_수가_딜러보다_높으면_이긴다() {
        //given
        Bettings bettings = new Bettings(List.of("10000", "20000"));
        Player player = new Player(new PlayerName("judy"),
                new Cards(List.of(
                        new Card(Suit.CLOVER, Denomination.KING),
                        new Card(Suit.HEART, Denomination.QUEEN),
                        new Card(Suit.HEART, Denomination.ACE))));


        Dealer dealer = new Dealer(
                            new Cards(List.of(
                                    new Card(Suit.CLOVER, Denomination.KING),
                                    new Card(Suit.HEART, Denomination.QUEEN))));

        Blackjack blackjack = new Blackjack(List.of(player));
        Players players = blackjack.getPlayers();
        Result result = blackjack.createResult(players, dealer, bettings);

        //when
        int expected = 1;
        int actual = result.getResult().get(player);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void 플레이어가_가진_수가_딜러와_같으면_이긴다() {
        //given
        Bettings bettings = new Bettings(List.of("10000", "20000"));
        Player player = new Player(new PlayerName("judy"),
                new Cards(List.of(
                        new Card(Suit.CLOVER, Denomination.KING),
                        new Card(Suit.HEART, Denomination.QUEEN))));


        Dealer dealer = new Dealer(
                new Cards(List.of(
                        new Card(Suit.CLOVER, Denomination.KING),
                        new Card(Suit.HEART, Denomination.QUEEN))));

        Blackjack blackjack = new Blackjack(List.of(player));
        Players players = blackjack.getPlayers();
        Result result = blackjack.createResult(players, dealer, bettings);

        //when
        int expected = 1;
        int actual = result.getResult().get(player);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void 생성_테스트() {
        //given, when
        Player player = new Player(new PlayerName("judy"),
                new Cards(List.of(
                        new Card(Suit.CLOVER, Denomination.KING),
                        new Card(Suit.HEART, Denomination.QUEEN))));

        //then
        Assertions.assertDoesNotThrow(() -> new Blackjack(List.of(player)));
    }
}
