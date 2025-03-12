package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CardGroup;
import domain.card.Deck;
import domain.card.RandomCardGenerator;
import domain.gamer.Dealer;
import domain.gamer.PlayerGroup;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GameManagerTest {

    @Test
    void 게임_매니저를_생성한다() {
        //given
        final List<String> playerNames = List.of("윌슨", "가이온");
        CardGroup cardGroup = new CardGroup();
        final Deck deck = Deck.of(new RandomCardGenerator());
        Dealer dealer = new Dealer(cardGroup);
        final PlayerGroup playerGroup = PlayerGroup.of(playerNames);

        //when
        final GameManager gameManager = new GameManager(dealer, playerGroup, deck);
        //then
        assertThat(gameManager).isInstanceOf(GameManager.class);
    }
}
