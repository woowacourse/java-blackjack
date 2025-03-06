package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.CardGroup;
import domain.card.CardType;
import domain.card.RandomCardGenerator;
import java.util.List;

import domain.gamer.Player;
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

    @Test
    void 버스트가_나기_전까지_카드를_더_받는다(){
        final List<String> playerNames = List.of("윌슨", "가이온");
        RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        CardGroup cardGroup = new CardGroup(List.of(randomCardGenerator.generate()));
        final Player player = new Player("윌슨",cardGroup);
        final GameManager gameManager = GameManager.create(new RandomCardGenerator(), playerNames);

        boolean isHitting = gameManager.isHitting(player);

        assertThat(isHitting).isTrue();
    }
}
