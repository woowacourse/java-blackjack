package blackjack.model.blackjackgame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.cards.Card;
import blackjack.model.cards.Cards;
import blackjack.model.deck.CardDeck;
import blackjack.model.deck.RandomIndexGenerator;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Player;
import blackjack.model.results.PlayerProfit;
import blackjack.model.results.PlayerResult;
import blackjack.model.results.Result;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @DisplayName("카드 두장을 지급한다.")
    @Test
    void distributeCards() {
        Dealer dealer = new Dealer();
        List<Player> players = List.of(
                new Player("daon"),
                new Player("ella")
        );
        CardDeck cardDeck = new CardDeck(new RandomIndexGenerator());
        BlackJackGame blackJackGame = new BlackJackGame(dealer, players, cardDeck);

        blackJackGame.distributeCards();
        List<Card> dealerCards = dealer.getCards().getCards();
        List<List<Card>> playerCards = players.stream()
                .map(Player::getCards)
                .map(Cards::getCards)
                .toList();

        assertAll(
                () -> assertThat(dealerCards).hasSize(2),
                () -> assertThat(playerCards.get(0)).hasSize(2),
                () -> assertThat(playerCards.get(1)).hasSize(2)
        );
    }

    @DisplayName("플레이어 카드상태를 업데이트 한다")
    @Test
    void update() {
        Dealer dealer = new Dealer();
        Player player = new Player("daon");
        List<Player> players = List.of(
                player,
                new Player("ella")
        );
        CardDeck cardDeck = new CardDeck(new RandomIndexGenerator());
        BlackJackGame blackJackGame = new BlackJackGame(dealer, players, cardDeck);

        blackJackGame.update(0);
        Player findPlayer = blackJackGame.getPlayers().get(0);

        assertThat(findPlayer.getCards().getCards()).hasSize(1);
    }

    @DisplayName("플레이어 게임 결과로 전체 플레이어의 점수를 계산한다")
    @Test
    void calculatePlayerProfit() {
        Dealer dealer = new Dealer();
        List<Player> players = List.of(new Player("ella"), new Player("daon"));
        PlayerResult playerResult = new PlayerResult(createResultsForBet());
        BlackJackGame blackJackGame = new BlackJackGame(dealer, players, new CardDeck(new RandomIndexGenerator()));

        PlayerProfit playerProfit = blackJackGame.calculatePlayerProfit(playerResult);

        assertThat(playerProfit.getResult().values()).containsExactly(4500, 4000, -5000, 0);
    }

    private Map<Player, Result> createResultsForBet() {
        Map<Player, Result> map = new LinkedHashMap<>();
        map.put(getPlayer("ella", 3000), Result.WIN_BY_BLACKJACK);
        map.put(getPlayer("daon", 4000), Result.WIN);
        map.put(getPlayer("lily", 5000), Result.LOSE);
        map.put(getPlayer("pobi", 6000), Result.PUSH);
        return map;
    }

    private Player getPlayer(String name, int betAmount) {
        Player player = new Player(name);
        player.betMoney(betAmount);
        return player;
    }
}
