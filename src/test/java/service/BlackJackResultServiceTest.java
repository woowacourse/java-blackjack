package service;

import domain.Dealer;
import domain.Player;
import domain.WinningStatus;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import dto.FinalResultDto;
import dto.ScoreResultDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackResultServiceTest {

    BlackJackResultService blackJackResultService;
    Dealer dealer;
    List<Player> players;

    @BeforeEach
    void setUp() {
        blackJackResultService = new BlackJackResultService();

        dealer = new Dealer();

        players = new ArrayList<>();
        players.add(new Player("봉구스"));
        players.add(new Player("시오"));
        players.add(new Player("영기"));
    }

    // 이것저것
    @Test
    void createScoreResultDto() {
        // given
        ScoreResultDto scoreResultDto = blackJackResultService.createScoreResultDto(dealer, players);

        // when, then
        assertNotNull(scoreResultDto);
    }

    // 승무패 하나씩
    @Test
    void createFinalResultDto() {
        // given
        dealer.draw(new Card(Suit.HEARTS, Rank.JACK));
        players.get(0).draw(new Card(Suit.CLUBS, Rank.NUM5));
        players.get(1).draw(new Card(Suit.CLUBS, Rank.JACK));
        players.get(2).draw(new Card(Suit.CLUBS, Rank.ACE));
        FinalResultDto finalResultDto = blackJackResultService.createFinalResultDto(dealer, players);

        // when, then
        assertEquals("딜러: 1승 1패",finalResultDto.finalResults().get(0));
        assertEquals("봉구스: 패",finalResultDto.finalResults().get(1));
        assertEquals("시오: 무",finalResultDto.finalResults().get(2));
        assertEquals("영기: 승",finalResultDto.finalResults().get(3));
    }
}