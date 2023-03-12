package domain;

import game.Blackjack;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlackjackTest {

    @Test
    void 딜러가_가진_수가_플레이어보다_높으면_딜러가_이긴다() {
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
        assertEquals(expected, actual);
    }
}
