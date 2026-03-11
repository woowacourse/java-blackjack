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

    // todo: 테스트 옮기기
    @Test
    void 플레이어가_Bust로_패배하는_경우() {
        // given
        Dealer dealer = createDealer(Rank.JACK);
        List<Player> players = List.of(
                createPlayer("봉구스", Rank.KING, Rank.QUEEN, Rank.JACK),
                createPlayer("시오"),
                createPlayer("영기")
        );

        FinalResultDto finalResultDto = FinalResultDto.of(dealer, players);

        // when, then
        assertEquals(3, finalResultDto.dealerWinCount());
        assertEquals(0, finalResultDto.dealerLoseCount());
        assertEquals("패", finalResultDto.playerResults().getFirst().result());
    }

    // todo: 테스트 옮기기
    @Test
    void 딜러가_Bust로_패배하는_경우() {
        // given
        Dealer dealer = createDealer(Rank.JACK, Rank.KING, Rank.QUEEN);
        List<Player> players = List.of(
                createPlayer("봉구스", Rank.KING),
                createPlayer("시오"),
                createPlayer("영기")
        );

        FinalResultDto finalResultDto = FinalResultDto.of(dealer, players);

        // when, then
        assertEquals(0, finalResultDto.dealerWinCount());
        assertEquals(3, finalResultDto.dealerLoseCount());
        assertEquals("승", finalResultDto.playerResults().getFirst().result());
    }
}
