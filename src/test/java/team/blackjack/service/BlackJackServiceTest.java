package team.blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Deck;
import team.blackjack.domain.Players;
import team.blackjack.service.dto.RevenueResult;
import team.blackjack.service.dto.ScoreResult;

class BlackJackServiceTest {

    private BlackjackService blackjackService;
    private BlackjackJudge blackjackJudge;
    private Players players;
    private Dealer dealer;
    private Deck deck;

    @BeforeEach
    void setUp() {
        blackjackJudge = new BlackjackJudge();
        deck = new Deck();
        dealer = new Dealer();
    }

    @Test
    void 플레이어_이름_목록으로_게임을_초기화한다() {
        players = new Players(Set.of("pobi", "jason"));
        blackjackService = new BlackjackService(blackjackJudge, players, dealer, deck);

        assertThat(blackjackService.getAllPlayerNames()).hasSize(2);
        assertThat(blackjackService.getAllPlayerNames()).containsExactlyInAnyOrder("pobi", "jason");
    }

    @Test
    void 게임초기화_후_각_플레이어와_딜러가_카드_2장씩_가진다() {
        players = new Players(Set.of("pobi"));
        blackjackService = new BlackjackService(blackjackJudge, players, dealer, deck);
        blackjackService.initParticipantCard();

        assertThat(blackjackService.getPlayerCardNames("pobi")).hasSize(2);

        ScoreResult scoreResult = blackjackService.getParticipantScoreResult();
        assertThat(scoreResult.dealerCard()).hasSize(2);
        assertThat(scoreResult.playerCards().get("pobi")).hasSize(2);
    }

    @Test
    void 플레이어가_hit_하는_경우_카드가_한_장_추가된다() {
        players = new Players(Set.of("pobi"));
        blackjackService = new BlackjackService(blackjackJudge, players, dealer, deck);
        blackjackService.initParticipantCard();

        int sizeBefore = blackjackService.getPlayerCardNames("pobi").size();
        blackjackService.hitPlayer("pobi");
        int sizeAfter = blackjackService.getPlayerCardNames("pobi").size();

        assertThat(sizeAfter).isEqualTo(sizeBefore + 1);
    }

    @Test
    void 딜러와_모든_플레이어_점수와_카드정보를_계산한다() {
        players = new Players(Set.of("pobi", "jason"));
        blackjackService = new BlackjackService(blackjackJudge, players, dealer, deck);
        blackjackService.initParticipantCard();

        ScoreResult scoreResult = blackjackService.getParticipantScoreResult();

        assertThat(scoreResult.playerNames()).containsExactlyInAnyOrder("pobi", "jason");
        assertThat(scoreResult.dealerCard()).isNotEmpty();
        assertThat(scoreResult.playerCards()).containsKeys("pobi", "jason");
        assertThat(scoreResult.playerScores()).containsKeys("pobi", "jason");
    }

    @Test
    void 딜러가_hit하는_경우_카드가_추가된다() {
        players = new Players(Set.of("pobi"));
        blackjackService = new BlackjackService(blackjackJudge, players, dealer, deck);
        blackjackService.initParticipantCard();

        int dealerCardsBefore = blackjackService.getParticipantScoreResult().dealerCard().size();
        blackjackService.hitDealer();
        int dealerCardsAfter = blackjackService.getParticipantScoreResult().dealerCard().size();

        assertThat(dealerCardsAfter).isEqualTo(dealerCardsBefore + 1);
    }

    @Test
    void 플레이어_배팅_후_수익_결과_반환() {
        players = new Players(Set.of("pobi"));
        blackjackService = new BlackjackService(blackjackJudge, players, dealer, deck);
        blackjackService.initParticipantCard();
        blackjackService.batMoney("pobi", 1000);

        RevenueResult revenueResult = blackjackService.getRevenueResult();

        assertThat(revenueResult.playerRevenueMap()).containsKey("pobi");
        assertThat(revenueResult.playerRevenueMap().get("pobi")).isNotNull();
        double playerSum = revenueResult.playerRevenueMap().values().stream().mapToDouble(Double::doubleValue).sum();
        assertThat(revenueResult.dealerRevenue()).isEqualTo(-playerSum);
    }
}
