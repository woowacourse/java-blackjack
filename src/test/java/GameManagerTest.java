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

        List<Player> records = manager.getPlayerSequence();
        Dealer dealer = new Dealer();

        assertThat(scoreResults.get(0).getHand().size()).isEqualTo(2);
        assertThat(scoreResults.get(0).getPlayerName()).isEqualTo("딜러");
        assertThat(scoreResults.get(1).getHand().size()).isEqualTo(2);
        assertThat(scoreResults.get(1).getPlayerName()).isEqualTo("pobi");
        assertThat(scoreResults.get(2).getHand().size()).isEqualTo(2);
        assertThat(scoreResults.get(2).getPlayerName()).isEqualTo("cary");
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

}