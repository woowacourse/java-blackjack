package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardGroup;
import domain.card.CardScore;
import domain.card.CardType;
import domain.card.Deck;
import domain.card.RandomCardGenerator;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.PlayerGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class GameManagerTest {

    @Test
    void 게임_매니저를_생성한다() {
        //given
        final List<String> playerNames = List.of("윌슨", "가이온");
        CardGroup cardGroup = new CardGroup(new ArrayList<>());
        final Deck deck = Deck.of(new RandomCardGenerator());
        Dealer dealer = new Dealer(cardGroup);
        final PlayerGroup playerGroup = PlayerGroup.of(playerNames);

        //when
        final GameManager gameManager = new GameManager(dealer, playerGroup, deck);
        //then
        assertThat(gameManager).isInstanceOf(GameManager.class);
    }

    @Test
    void 플레이어만_블랙잭인_경우_수익률_결과를_계산한다() {
        //given
        final List<Card> cards1 = List.of(new Card(CardType.HEART, CardScore.JACK),
                new Card(CardType.DIAMOND, CardScore.ACE));
        final List<Card> cards2 = List.of(new Card(CardType.DIAMOND, CardScore.TWO),
                new Card(CardType.HEART, CardScore.QUEEN));
        final List<Card> cards3 = List.of(new Card(CardType.CLOVER, CardScore.TWO),
                new Card(CardType.HEART, CardScore.TWO));
        final List<Player> players = List.of(
                new Player("윌슨", new CardGroup(cards1), new Betting(10_000)),
                new Player("가이온", new CardGroup(cards2), new Betting(10_000)),
                new Player("수달", new CardGroup(cards3), new Betting(20_000)));
        final PlayerGroup playerGroup = new PlayerGroup(players);
        final Dealer dealer = new Dealer(new CardGroup(cards2));
        final Deck deck = Deck.of(new RandomCardGenerator());

        //when
        final GameManager gameManager = new GameManager(dealer, playerGroup, deck);
        final Map<String, Double> result = gameManager.calculatePlayerBettingAmountOfReturn();

        //then
        assertThat(result).containsEntry("윌슨", 15_000.0)
                .containsEntry("가이온", 0.0)
                .containsEntry("수달", -20_000.0);
    }
}
