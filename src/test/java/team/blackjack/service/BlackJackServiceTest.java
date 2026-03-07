package team.blackjack.service;

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
    void 플레이어_이름_목록으로_게임을_초기화한다() {
        blackJackService.initGame(List.of("pobi", "jason"));

        List<Player> players = blackJackService.getPlayer();
        assertThat(players).hasSize(2);
        assertThat(players.stream().map(Player::getName).toList())
                .containsExactly("pobi", "jason");
    }

    @Test
    void 게임초기화_후_각_플레이어와_딜러가_카드_2장씩_가진다() {
        blackJackService.initGame(List.of("pobi"));
        blackJackService.drawInitialCards();

        List<Player> players = blackJackService.getPlayer();
        assertThat(players.getFirst().getHands().getFirst().getCards()).hasSize(2);

        ScoreResult scoreResult = blackJackService.calculateAllParticipantScore();
        assertThat(scoreResult.dealerCard()).hasSize(2);
        assertThat(scoreResult.playerCards().get("pobi")).hasSize(2);
    }

    @Test
    void 플레이어가_hit_하는_경우_카드가_한_장_추가된다() {
        blackJackService.initGame(List.of("pobi"));
        blackJackService.drawInitialCards();

        Player pobi = blackJackService.getPlayer().getFirst();
        int sizeBefore = pobi.getHands().getFirst().getCards().size();

        blackJackService.hitPlayer(pobi);

        assertThat(pobi.getHands().getFirst().getCards()).hasSize(sizeBefore + 1);
    }

    @Test
    void 딜러와_모든_플레이어_점수와_카드정보를_계산한다() {
        blackJackService.initGame(List.of("pobi", "jason"));
        blackJackService.drawInitialCards();

        var scoreResult = blackJackService.calculateAllParticipantScore();

        assertThat(scoreResult.playerNames()).containsExactly("pobi", "jason");
        assertThat(scoreResult.dealerCard()).isNotEmpty();
        assertThat(scoreResult.playerCards()).containsKeys("pobi", "jason");
        assertThat(scoreResult.playerScores()).containsKeys("pobi", "jason");

    }

    @Test
    void 딜러가_hit하는_경우_카드가_추가된다() {
        blackJackService.initGame(List.of("pobi"));
        blackJackService.drawInitialCards();

        int dealerCardsBefore = blackJackService.calculateAllParticipantScore().dealerCard().size();
        blackJackService.hitDealer();
        int dealerCardsAfter = blackJackService.calculateAllParticipantScore().dealerCard().size();

        assertThat(dealerCardsAfter).isEqualTo(dealerCardsBefore + 1);
    }
}
