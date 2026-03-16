package domain;

import domain.card.Deck;
import dto.GameInitialInfoDto;
import dto.GameResultDto;
import dto.GameScoreResultDto;
import domain.game.GameManager;
import domain.participant.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameManagerTest {

    @Test
    void 등록된_플레이어와_딜러_순서대로_카드를_돌린다() {
        GameManager manager = new GameManager(new Deck());

        manager.registerPlayers(
                List.of("pobi", "cary"),
                List.of(new BettingMoney(1000), new BettingMoney(1000))
        );

        manager.startGame();
        List<GameScoreResultDto> scoreResults = manager.getScoreResults();

        assertThat(scoreResults.get(0).getHand().size()).isEqualTo(2);
        assertThat(scoreResults.get(0).getPlayerName()).isEqualTo("딜러");
        assertThat(scoreResults.get(1).getHand().size()).isEqualTo(2);
        assertThat(scoreResults.get(1).getPlayerName()).isEqualTo("pobi");
        assertThat(scoreResults.get(2).getHand().size()).isEqualTo(2);
        assertThat(scoreResults.get(2).getPlayerName()).isEqualTo("cary");
    }

    @Test
    void 딜러의_카드는_한_장만_공개한다() {
        GameManager manager = new GameManager(new Deck());

        manager.registerPlayers(
                List.of("pobi"),
                List.of(new BettingMoney(1000), new BettingMoney(1000))
        );

        manager.startGame();
        List<GameInitialInfoDto> initialInfo = manager.getInitialInfo();

        assertThat(initialInfo.getFirst().getHand().size()).isEqualTo(1);
    }

    @Test
    void 플레이어의_카드는_두_장_공개한다() {
        GameManager manager = new GameManager(new Deck());

        manager.registerPlayers(
                List.of("pobi"),
                List.of(new BettingMoney(1000))
        );

        manager.startGame();
        List<GameInitialInfoDto> initialInfo = manager.getInitialInfo();

        assertThat(initialInfo.get(1).getHand().size()).isEqualTo(2);
    }

    @Test
    void 플레이어를_한명_등록한다() {
        GameManager manager = new GameManager(new Deck());

        manager.registerPlayers(
                List.of("pobi"),
                List.of(new BettingMoney(1000), new BettingMoney(1000))
        );

        List<Player> result = manager.getPlayersToPlay();

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getName()).isEqualTo("pobi");
    }

    @Test
    void 플레이어를_세명_등록한다() {
        GameManager manager = new GameManager(new Deck());

        manager.registerPlayers(
                List.of("pobi", "cary", "rudy"),
                List.of(new BettingMoney(1000), new BettingMoney(1000), new BettingMoney(1000))
        );

        List<Player> result = manager.getPlayersToPlay();

        assertThat(result).hasSize(3);
    }

    @Test
    void 플레이어가_카드를_한장_더_받는다() {
        GameManager manager = new GameManager(new Deck());

        manager.registerPlayers(
                List.of("pobi"),
                List.of(new BettingMoney(1000))
        );

        manager.startGame();
        Player player = manager.getPlayersToPlay().getFirst();

        int before = player.getHandToString().size();

        manager.drawPlayerCard(player);

        int after = player.getHandToString().size();

        assertThat(after).isEqualTo(before + 1);
    }

    @Test
    void 딜러가_카드를_뽑으면_true를_반환한다() {
        GameManager manager = new GameManager(new Deck());

        boolean result = manager.proceedDealerTurn();

        assertThat(result).isTrue();
    }

    @Test
    void 게임_최종_결과에는_딜러와_모든_플레이어_결과가_포함된다() {
        GameManager manager = new GameManager(new Deck());

        manager.registerPlayers(
                List.of("pobi", "cary"),
                List.of(new BettingMoney(1000), new BettingMoney(1000))
        );

        GameResultDto result = manager.getFinalResult();

        assertThat(result.getDealerResult().getDealerName()).isEqualTo("딜러");
        assertThat(result.getPlayerResults()).hasSize(2);
        assertThat(result.getPlayerResults().get(0).getPlayerName()).isEqualTo("pobi");
        assertThat(result.getPlayerResults().get(1).getPlayerName()).isEqualTo("cary");
    }

    @Test
    void 플레이어가_없는_경우_초기정보에는_딜러만_포함된다() {
        GameManager manager = new GameManager(new Deck());

        manager.registerPlayers(List.of(), List.of());
        manager.startGame();

        List<GameInitialInfoDto> result = manager.getInitialInfo();

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getPlayerName()).isEqualTo("딜러");
    }

    @Test
    void 플레이어가_없는_경우_점수결과에는_딜러만_포함된다() {
        GameManager manager = new GameManager(new Deck());

        manager.registerPlayers(List.of(), List.of());
        manager.startGame();

        List<GameScoreResultDto> result = manager.getScoreResults();

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getPlayerName()).isEqualTo("딜러");
    }

    @Test
    void 플레이어가_없는_경우_최종결과에는_딜러만_포함된다() {
        GameManager manager = new GameManager(new Deck());

        manager.registerPlayers(List.of(), List.of());
        manager.startGame();

        GameResultDto result = manager.getFinalResult();

        assertThat(result.getDealerResult().getDealerName()).isEqualTo("딜러");
        assertThat(result.getPlayerResults()).isEmpty();
    }
}