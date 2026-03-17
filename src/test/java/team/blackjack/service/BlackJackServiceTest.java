package team.blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Deck;
import team.blackjack.domain.Player;
import team.blackjack.domain.Players;
import team.blackjack.service.dto.ScoreResult;

class BlackJackServiceTest {

    private BlackjackService blackjackService;
    private BlackjackJudge blackjackJudge;

    @BeforeEach
    void setUp() {
        blackjackJudge = new BlackjackJudge();
        blackjackService = new BlackjackService(blackjackJudge);
    }

    @Test
    void 플레이어_이름_목록으로_게임을_초기화한다() {
        Players players = new Players(List.of("pobi", "jason"));

        List<String> playerNames = blackjackService.getAllPlayerNames(players);

        assertThat(playerNames).hasSize(2);
        assertThat(playerNames).containsExactly("pobi", "jason");
    }

    @Test
    void 게임초기화_후_각_플레이어와_딜러가_카드_2장씩_가진다() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));

        blackjackService.drawInitialCards(deck, players, dealer);

        ScoreResult scoreResult = blackjackService.calculateAllParticipantScore(players, dealer);
        assertThat(scoreResult.dealerCard()).hasSize(2);
        assertThat(scoreResult.playerCards().get("pobi")).hasSize(2);
    }

    @Test
    void 플레이어가_hit_하는_경우_카드가_한_장_추가된다() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        blackjackService.drawInitialCards(deck, players, dealer);

        Player pobi = players.getPlayerByName("pobi");
        int sizeBefore = pobi.getCards().size();

        blackjackService.hit(deck, pobi);

        assertThat(pobi.getCards()).hasSize(sizeBefore + 1);
    }

    @Test
    void 딜러와_모든_플레이어_점수와_카드정보를_계산한다() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi", "jason"));
        blackjackService.drawInitialCards(deck, players, dealer);

        ScoreResult scoreResult = blackjackService.calculateAllParticipantScore(players, dealer);

        assertThat(scoreResult.playerNames()).containsExactly("pobi", "jason");
        assertThat(scoreResult.dealerCard()).isNotEmpty();
        assertThat(scoreResult.playerCards()).containsKeys("pobi", "jason");
        assertThat(scoreResult.playerScores()).containsKeys("pobi", "jason");
    }

    @Test
    void 딜러가_hit하는_경우_카드가_추가된다() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi"));
        blackjackService.drawInitialCards(deck, players, dealer);

        int dealerCardsBefore = dealer.getCards().size();
        blackjackService.hit(deck, dealer);
        int dealerCardsAfter = dealer.getCards().size();

        assertThat(dealerCardsAfter).isEqualTo(dealerCardsBefore + 1);
    }
}
