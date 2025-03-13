package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CardGroup;
import domain.card.Deck;
import domain.card.RandomCardGenerator;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.PlayerGroup;
import java.util.List;
import java.util.Map;
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

    @Test
    void 플레이어의_배팅_결과를_계산한다() {
        //given
        final List<Player> players = List.of(
                new Player("윌슨", new CardGroup(), 10_000),
                new Player("가이온", new CardGroup(), 10_000),
                new Player("수달", new CardGroup(), 20_000));
        final PlayerGroup playerGroup = new PlayerGroup(players);
        final Dealer dealer = new Dealer(new CardGroup());
        final Deck deck = Deck.of(new RandomCardGenerator());
        final Map<String, GameResult> playersGameResult = Map.of("윌슨", GameResult.WIN, "가이온", GameResult.DRAW, "수달",
                GameResult.LOSE);

        //when
        final GameManager gameManager = new GameManager(dealer, playerGroup, deck);
        final Map<String, Integer> result = gameManager.calculatePlayerBattingAmountOfReturn(
                playersGameResult);

        //then
        assertThat(result).containsEntry("윌슨", 10_000)
                .containsEntry("가이온", 0)
                .containsEntry("수달", -20_000);

    }
}
