import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameManagerTest {

    @Test
    void 등록된_플레이어와_딜러_순서대로_카드를_돌린다() {
        GameManager manager = new GameManager();

        manager.addPlayer("pobi");
        manager.addPlayer("cary");

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
        GameManager manager = new GameManager();

        manager.addPlayer("pobi");

        manager.startGame();
        List<GameInitialInfoDto> initialInfo = manager.getInitialInfo();

        assertThat(initialInfo.getFirst().getHand().size()).isEqualTo(1);
    }

    @Test
    void 플레이어의_카드는_두_장_공개한다() {
        GameManager manager = new GameManager();

        manager.addPlayer("pobi");

        manager.startGame();
        List<GameInitialInfoDto> initialInfo = manager.getInitialInfo();

        assertThat(initialInfo.get(1).getHand().size()).isEqualTo(2);
    }

    @Test
    void 플레이어를_한명_등록한다() {
        GameManager manager = new GameManager();
        manager.addPlayer("pobi");

        List<Player> result = manager.getPlayerSequence();

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.getFirst().getName()).isEqualTo("pobi");
    }

    @Test
    void 플레이어를_세명_등록한다() {
        GameManager manager = new GameManager();
        List<String> playerNames = List.of("pobi", "cary", "rudy");

        for (String playerName : playerNames) {
            manager.addPlayer(playerName);
        }

        List<Player> result = manager.getPlayerSequence();

        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void 게임을_시작하면_카드를_2장씩_나눠준다() {
        GameManager manager = new GameManager();
    }

    @Test
    void 합계가_21점이면_블랙잭이다() {
        GameManager manager = new GameManager();
        Player player = new Player("pobi");
        player.addCard(new Card(Rank.ACE, Suit.SPADE));
        player.addCard(new Card(Rank.KING, Suit.SPADE));

        assertThat(manager.isBlackjack(player)).isTrue();
    }

}