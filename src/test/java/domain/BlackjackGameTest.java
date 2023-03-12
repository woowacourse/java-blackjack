package domain;

import domain.deck.Card;
import domain.deck.CardDeck;
import domain.deck.CardNumber;
import domain.deck.CardPattern;
import domain.generator.CardGenerator;
import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;
import domain.strategy.NoShuffleCardsStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGameTest {
    private Players players;
    private CardDeck cardDeck;

    @BeforeEach
    void set() {
        Player player1 = new Player("pobi",new BettingMoney(0));
        Player player2 = new Player("jason",new BettingMoney(0));
        Dealer dealer = new Dealer();
        players = new Players(List.of(dealer,player1, player2));
        CardGenerator cardGenerator = new CardGenerator();
        cardDeck = new CardDeck(cardGenerator.generate(new NoShuffleCardsStrategy()));
    }

    @Test
    @DisplayName("딜러에게 카드를 1장 나눠준다.")
    void distributeDealerInitialCardsTest() {
        BlackjackGame blackjackGame = new BlackjackGame(players, cardDeck);

        Dealer dealer = players.findDealer();
        List<Card> playerCard = new ArrayList<>();
        playerCard.add(new Card(CardNumber.ACE, CardPattern.SPADE));

        blackjackGame.distributeDealer();

        Assertions.assertThat(dealer.getPlayerCards()).usingRecursiveComparison().isEqualTo(playerCard);
    }

    @Test
    @DisplayName("플레이어에게 카드를 1장 나눠준다.")
    void distributePlayersInitialCardsTest() {
        BlackjackGame blackjackGame = new BlackjackGame(players, cardDeck);

        Player playerPobi = players.getPlayersWithOutDealer().get(0);
        List<Card> playerCard = new ArrayList<>();
        playerCard.add(new Card(CardNumber.ACE, CardPattern.SPADE));

        blackjackGame.distributePlayers();

        Assertions.assertThat(playerPobi.getPlayerCards()).usingRecursiveComparison().isEqualTo(playerCard);
    }
}
