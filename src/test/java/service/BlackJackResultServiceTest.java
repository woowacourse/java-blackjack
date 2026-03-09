package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static util.TestUtil.createDealer;
import static util.TestUtil.createPlayer;

import domain.Dealer;
import domain.Player;
import domain.card.Rank;
import dto.FinalResultDto;
import dto.ScoreResultDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackJackResultServiceTest {

    BlackJackResultService blackJackResultService;

    @BeforeEach
    void setUp() {
        blackJackResultService = new BlackJackResultService();
    }

    @Test
    void createScoreResultDto() {
        // given
        Dealer dealer = createDealer();
        List<Player> players = List.of();
        ScoreResultDto scoreResultDto = blackJackResultService.createScoreResultDto(dealer, players);

        // when, then
        assertNotNull(scoreResultDto);
    }

    @Test
    void createFinalResultDto() {
        // given
        Dealer dealer = createDealer(Rank.JACK);
        List<Player> players = List.of(
                createPlayer("봉구스", Rank.FIVE),
                createPlayer("시오", Rank.JACK),
                createPlayer("영기", Rank.ACE));
        FinalResultDto finalResultDto = blackJackResultService.createFinalResultDto(dealer, players);

        // when, then
        assertEquals("딜러: 1승 1패", finalResultDto.finalResults().get(0));
        assertEquals("봉구스: 패", finalResultDto.finalResults().get(1));
        assertEquals("시오: 무", finalResultDto.finalResults().get(2));
        assertEquals("영기: 승", finalResultDto.finalResults().get(3));
    }

    @Test
    void 플레이어가_Bust로_패배하는_경우() {
        // given
        Dealer dealer = createDealer(Rank.JACK);
        List<Player> players = List.of(
                createPlayer("봉구스", Rank.KING, Rank.QUEEN, Rank.JACK),
                createPlayer("시오"),
                createPlayer("영기")
        );

        FinalResultDto finalResultDto = blackJackResultService.createFinalResultDto(dealer, players);

        // when, then
        assertEquals("딜러: 3승 0패", finalResultDto.finalResults().get(0));
        assertEquals("봉구스: 패", finalResultDto.finalResults().get(1));
    }

    @Test
    void 딜러가_Bust로_패배하는_경우() {
        // given
        Dealer dealer = createDealer(Rank.JACK, Rank.KING, Rank.QUEEN);
        List<Player> players = List.of(
                createPlayer("봉구스", Rank.KING),
                createPlayer("시오"),
                createPlayer("영기")
        );

        FinalResultDto finalResultDto = blackJackResultService.createFinalResultDto(dealer, players);

        // when, then
        assertEquals("딜러: 0승 3패", finalResultDto.finalResults().get(0));
        assertEquals("봉구스: 승", finalResultDto.finalResults().get(1));
    }
}
