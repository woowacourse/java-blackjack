package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.RandomCardGenerator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GameManagerTest {

    @Test
    void 게임_매니저를_생성한다() {
        //given
        final List<String> playerNames = List.of("윌슨", "가이온");
        //when
        final GameManager gameManager = GameManager.create(new RandomCardGenerator(), playerNames);
        //then
        assertThat(gameManager).isInstanceOf(GameManager.class);
    }

    @Test
    void 딜러_점수가_16이하면_카드를_계속_뽑는다(){
        //given
        final List<String> playerNames = List.of("윌슨", "가이온");
        //when
        final GameManager gameManager = GameManager.create(new RandomCardGenerator(), playerNames);
        //then
        assertThatCode(gameManager::receiveCardToDealer).doesNotThrowAnyException();
    }
}
