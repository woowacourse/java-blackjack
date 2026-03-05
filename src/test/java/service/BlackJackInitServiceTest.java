package service;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackInitServiceTest {

    BlackJackInitService blackJackInitService;

    @BeforeEach
    void setUp() {
        blackJackInitService = new BlackJackInitService();
    }

    @Test
    void 덱을_정상적으로_생성() {
        // given, when
        Deck deck = blackJackInitService.createDeck();

        //then
        assertNotNull(deck);
    }

    @Test
    void 딜러를_정상적으로_생성() {
        //given, when
        Deck deck = new Deck();
        Dealer dealer = blackJackInitService.createDealer(deck);

        // then
        assertNotNull(dealer);
    }

    @Test
    void 플레이어들을_정상적으로_생성() {
        //given, when
        Deck deck = new Deck();
        List<Player> players = blackJackInitService.createPlayers(List.of("봉구스","시오"), deck);

        // then
        assertNotNull(players);
        assertEquals(2,players.size());
    }
}
