package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static util.TestUtil.createDealer;
import static util.TestUtil.createPlayer;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.card.Rank;
import dto.InitStatusDto;
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
        Deck deck = new Deck();
        Dealer dealer = blackJackInitService.createDealer(deck);

        // then
        assertNotNull(dealer);
    }

    @Test
    void 플레이어들을_정상적으로_생성() {
        //given, when
        Deck deck = new Deck();
        List<Player> players = blackJackInitService.createPlayers(List.of("봉구스", "시오"), deck);

        // then
        assertNotNull(players);
        assertEquals(2, players.size());
    }

    @Test
    void 초기_상태_Dto를_생성하는_경우() {
        //given
        Dealer dealer = createDealer(Rank.EIGHT);
        List<Player> players = List.of(
                createPlayer("봉구스"),
                createPlayer("시오"));

        // when
        InitStatusDto initStatusDto = blackJackInitService.createInitStatusDto(dealer, players);
        // then
        assertNotNull(initStatusDto);
    }
}
