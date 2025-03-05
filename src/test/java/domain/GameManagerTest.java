package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.RandomCardGenerator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GameManagerTest {

    @Test
    void 게임_매니저를_생성한다() {
        //given
        final List<String> playerNames = List.of("윌슨", "가이온");
        //when
        final GameManager gameManager = GameManager.creat(new RandomCardGenerator(), playerNames);
        //then
        assertThat(gameManager).isInstanceOf(GameManager.class);
    }
}
