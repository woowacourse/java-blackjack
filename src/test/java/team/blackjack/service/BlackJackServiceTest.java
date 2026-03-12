package team.blackjack.service;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.blackjack.domain.Player;

import java.util.List;
import team.blackjack.service.dto.ScoreResult;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackServiceTest {

    private BlackJackService blackJackService;

    @BeforeEach
    void setUp() {
        blackJackService = new BlackJackService();
    }

    @Test
    void 게임초기화_후_각_플레이어와_딜러가_카드_2장씩_가진다() {
        blackJackService.initGame(Map.of("pobi", 10000));
        blackJackService.dealInitialCards();

        List<Player> players = blackJackService.getPlayer();
        assertThat(players.getFirst().getHands().getFirst().getCards()).hasSize(2);

        ScoreResult scoreResult = blackJackService.calculateAllParticipantScore();
        assertThat(scoreResult.dealerCards()).hasSize(2);
        assertThat(scoreResult.playerCards().get("pobi")).hasSize(2);
    }

    @Test
    void 플레이어가_hit_하는_경우_카드가_한_장_추가된다() {
        blackJackService.initGame(Map.of("pobi", 10000));
        blackJackService.dealInitialCards();

        Player pobi = blackJackService.getPlayer().getFirst();
        int sizeBefore = pobi.getHands().getFirst().getCards().size();

        blackJackService.playerHit(pobi);

        assertThat(pobi.getHands().getFirst().getCards()).hasSize(sizeBefore + 1);
    }

    @Test
    void 딜러와_모든_플레이어_점수와_카드정보를_계산한다() {
        blackJackService.initGame(Map.of("pobi", 10000, "jason", 20000));
        blackJackService.dealInitialCards();

        var scoreResult = blackJackService.calculateAllParticipantScore();

        assertThat(scoreResult.playerNames()).containsExactlyInAnyOrder("pobi", "jason");
        assertThat(scoreResult.dealerCards()).isNotEmpty();
        assertThat(scoreResult.playerCards()).containsKeys("pobi", "jason");
        assertThat(scoreResult.playerScores()).containsKeys("pobi", "jason");
    }

    @Test
    void 딜러가_hit하는_경우_카드가_추가된다() {
        blackJackService.initGame(Map.of("pobi", 10000));
        blackJackService.dealInitialCards();

        int dealerCardsBefore = blackJackService.calculateAllParticipantScore().dealerCards().size();
        blackJackService.dealerHit();
        int dealerCardsAfter = blackJackService.calculateAllParticipantScore().dealerCards().size();

        assertThat(dealerCardsAfter).isEqualTo(dealerCardsBefore + 1);
    }
}
