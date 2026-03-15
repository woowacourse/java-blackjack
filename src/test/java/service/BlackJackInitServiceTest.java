package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import domain.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Deck deck = Deck.createShuffledDeck();
        Dealer dealer = blackJackInitService.createDealer(deck);

        // then
        assertNotNull(dealer);
    }

    @Test
    void 플레이어들을_정상적으로_생성() {
        //given, when
        Deck deck = Deck.createShuffledDeck();
        List<Player> players = blackJackInitService.createPlayers(List.of("봉구스", "시오"), deck);

        // then
        assertNotNull(players);
        assertEquals(2, players.size());
    }
}
